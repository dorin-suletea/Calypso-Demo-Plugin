package implementation.parser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bridge.transferable.interfaces.ConnectorModelInterface;
import bridge.transferable.interfaces.DataModelInterface;
import bridge.transferable.interfaces.EntityModelInterface;
import bridge.transferable.interfaces.SheetModelInterface;

public class JavaParser {
	private static JavaParser instance = new JavaParser();
	public static final String IN_DATA_MEMBERS_TAG = "Fields";
	public static final String IN_DATA_METHODS_TAG = "Methods";
	public static final String IN_DATA_PRIVATE_TAG = "-";
	public static final String IN_DATA_PROTECTED_TAG = "#";
	public static final String IN_DATA_PUBLIC_TAG = "+";

	public static final String STATIC_TAG = "static";
	public static final String PARAMETER_SEPARATOR = ",";

	public static final String CON_TYPE_TAG_INHERITANCE = "Inheritance";
	public static final String CON_TYPE_TAG_COMPOSITION = "Composition";

	public static final String INTERFACE_ENT_TYPE_TAG = "Interface";
	public static final String CLASS_ENT_TYPE_TAG = "Class";

	private JavaParser() {
	}

	public static JavaParser getInstance() {
		return instance;
	}

	public ArrayList<String> parseRoutine(SheetModelInterface parsedSheet) throws ParseException {
		ArrayList<String> sheetCodeColection = new ArrayList<String>();

		StringBuffer returnClassText = new StringBuffer();

		StringBuffer entityIDLine = new StringBuffer();
		StringBuffer membersBatch = new StringBuffer();
		StringBuffer methodsBatch = new StringBuffer();

		for (EntityModelInterface topLvlEntitie : parsedSheet.getTopLevelEnities()) {
			// parsing ent signature
			entityIDLine.append("public ");
			entityIDLine.append(topLvlEntitie.getType().toLowerCase() + " " + topLvlEntitie.getName() + " ");
			// !!
			// parsing inner data
			for (DataModelInterface data : topLvlEntitie.getDataRows()) {
				if (data.getIdentifier().equals(IN_DATA_MEMBERS_TAG)) {
					for (String line : data.getDataLine())
						parsMembers(membersBatch, line);
				}
				if (data.getIdentifier().equals(IN_DATA_METHODS_TAG)) {
					for (String line : data.getDataLine())
						parsMethods(methodsBatch, line);
				}
			}
			// !!

			// parsing connectors
			StringBuffer implementsInterfaceText = new StringBuffer();
			boolean doesImplement = false;
			implementsInterfaceText.append("implements ");

			StringBuffer extendsClassText = new StringBuffer();
			boolean doesExtendClass = false;
			extendsClassText.append("extends ");

			for (ConnectorModelInterface con : parsedSheet.getConnectors())
				if (con.getFrom().equals(topLvlEntitie)) {
					if (CON_TYPE_TAG_INHERITANCE.equals(con.getDrawnType())) {
						if (con.getTo().getType().equals(INTERFACE_ENT_TYPE_TAG)) {
							implementsInterfaceText.append(con.getTo().getName() + ",");
							for (String s : con.getTo().getDataRows().get(0).getDataLine()) {
								parsMethods(methodsBatch, s);
							}
							doesImplement = true;
						}
						if (con.getTo().getType().equals(CLASS_ENT_TYPE_TAG)) {
							if (doesExtendClass)
								throw new ParseException("Java does not suport multiple inheritance : ");
							extendsClassText.append(con.getTo().getName());

							doesExtendClass = true;
						}
					}
					if (CON_TYPE_TAG_COMPOSITION.equals(con.getDrawnType())) {
						membersBatch.append("\n\tprivate List<" + con.getTo().getName() + ">" + " " + con.getTo().getName().toLowerCase() + "List;");
					}
				}

			for (ConnectorModelInterface con : parsedSheet.getConnectors())
				if (con.getTo().equals(topLvlEntitie)) {
					if (CON_TYPE_TAG_COMPOSITION.equals(con.getDrawnType())) {
						if (!con.getEndText().isEmpty())
							membersBatch.append("\n\tprivate List<" + con.getFrom().getName() + ">" + " " + con.getFrom().getName().toLowerCase() + "List;");
					}
				}

			if (doesExtendClass)
				entityIDLine.append(extendsClassText + " ");

			implementsInterfaceText.replace(implementsInterfaceText.length() - 1, implementsInterfaceText.length(), "");// remove
																														// last
																														// char
			if (doesImplement)
				entityIDLine.append(implementsInterfaceText);

			// !!

			// assembling resoult for entity
			entityIDLine.append("{");
			returnClassText.append(entityIDLine);
			returnClassText.append(membersBatch);
			returnClassText.append(methodsBatch);
			returnClassText.append("\n}\n");
			sheetCodeColection.add(returnClassText.toString());

			returnClassText = new StringBuffer();
			entityIDLine = new StringBuffer();
			membersBatch = new StringBuffer();
			methodsBatch = new StringBuffer();
			// !!
		}

		return sheetCodeColection;
	}

	private void parsMembers(StringBuffer resoult, String source) throws ParseException {
		String sourceClone = new String(source);
		try {
			resoult.append("\n\t");
			sourceClone = sourceClone.replaceAll(" ", "");// triming

			if (sourceClone.startsWith(IN_DATA_PRIVATE_TAG))
				resoult.append("private");

			if (sourceClone.startsWith(IN_DATA_PUBLIC_TAG))
				resoult.append("public");

			if (sourceClone.startsWith(IN_DATA_PROTECTED_TAG))
				resoult.append("protected");

			sourceClone = sourceClone.substring(1);// remove first char

			// switch order from ult to java
			String[] sides = new String[2];
			sides = sourceClone.split(":");
			resoult.append(" " + sides[1]);

			resoult.append(" " + sides[0] + ";");
			// !!

		} catch (Throwable e) {
			throw new ParseException("Invalid member definition " + source);
		}
	}

	private void parsMethods(StringBuffer resoult, String source) throws ParseException {
		String sourceClone = new String(source);
		try {
			sourceClone = sourceClone.replaceAll(" ", "");// triming
			resoult.append("\n\t");

			if (sourceClone.startsWith(IN_DATA_PRIVATE_TAG))
				resoult.append("private");

			if (sourceClone.startsWith(IN_DATA_PUBLIC_TAG))
				resoult.append("public");

			if (sourceClone.startsWith(IN_DATA_PROTECTED_TAG))
				resoult.append("protected");

			sourceClone = sourceClone.substring(1);// remove first char

			if (sourceClone.contains(STATIC_TAG)) {
				resoult.append(" static");
				sourceClone = sourceClone.replaceFirst(STATIC_TAG, "");// clear
																		// static
																		// identifier
			}

			// identify paranthesis
			Pattern p = Pattern.compile("\\(.*\\)");
			Matcher m = p.matcher(sourceClone);
			int occuranceNr = 0;// if diferent from 1 after the while something
								// went wrong
			String inParanthesisText = null;
			while (m.find()) {
				inParanthesisText = m.group();
				occuranceNr++;
			}
			if (occuranceNr != 1)
				throw new ParseException("The method line can not be parsed : " + source);

			sourceClone = sourceClone.replace(inParanthesisText, "");// remove
																		// item

			inParanthesisText = inParanthesisText.substring(1, inParanthesisText.length() - 1);// remove
																								// the
																								// parenthesis

			// parsim method's params
			StringBuffer parsedParams = new StringBuffer();
			if (inParanthesisText.length() > 0) {
				String[] paramList = inParanthesisText.split(PARAMETER_SEPARATOR);
				parsedParams.append("(");
				for (String px : paramList) {
					String[] sides = new String[2];
					sides = px.split(":");
					parsedParams.append(sides[1]);
					parsedParams.append(" " + sides[0] + ",");
				}
				parsedParams.replace(parsedParams.length() - 1, parsedParams.length(), "");
				parsedParams.append(")");
			} else {
				parsedParams.append("()");
			}
			// !!

			// rotate return type and name
			String[] sides = new String[2];
			sides = sourceClone.split(":");
			resoult.append(" " + sides[1]);
			resoult.append(" " + sides[0]);
			// !!

			resoult.append(parsedParams);
			resoult.append(";");

		} catch (Throwable e) {
			throw new ParseException("The method line can not be parsed : " + source);
		}
	}
}
