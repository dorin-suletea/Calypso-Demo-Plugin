package implementation;

import bridge.transferable.interfaces.EntityModelInterface;

public class Main {
	public static EntityModelInterface ent1;
	public static EntityModelInterface ent2;
	public static EntityModelInterface ent3;


//	public static void main(String[] args) {
//		PluginImplementation.sheetView.ge
//	}
//		SheetModelInterface shM = new SheetModelInterface() {
//
//			@Override
//			public void setSheetSize(Dimension sheetSize) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void setSheetName(String sheetName) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public void setCommited(boolean isCommited) {
//				// TODO Auto-generated method stub
//
//			}
//
//			@Override
//			public boolean remove(ConnectorModelInterface o) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public ConnectorModelInterface remove(int index) {
//				// TODO Auto-generated method stub
//				return null;
//			}
//
//			@Override
//			public boolean remove(EntityModelInterface o) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public ArrayList<EntityModelInterface> getTopLevelEnities() {
//				ArrayList<EntityModelInterface> ret = new ArrayList<EntityModelInterface>();
//				ret.add(new EntityModelInterface() {
//
//					@Override
//					public void setSize(Dimension size) {
//					}
//
//					@Override
//					public void setName(String name) {
//					}
//
//					@Override
//					public void setLocation(Point location) {
//					}
//
//					@Override
//					public String getType() {
//						return "class";
//					}
//
//					@Override
//					public Dimension getSize() {
//						return null;
//					}
//
//					@Override
//					public String getName() {
//						return "Main";
//					}
//
//					@Override
//					public Point getLocation() {
//						return null;
//					}
//
//					@Override
//					public List<DataModelInterface> getDataRows() {
//						ArrayList<DataModelInterface> ret = new ArrayList<DataModelInterface>();
//						ret.add(new DataModelInterface() {
//
//							@Override
//							public String getIdentifier() {
//								return "methods";
//							}
//
//							@Override
//							public ArrayList<String> getDataLine() {
//								ArrayList<String> ret = new ArrayList<String>();
//								ret.add("+static main(args[]:String):void");
//								ret.add("+setMainWindow(assoctiatedMainWindow:MainWindowProxy,val:int" + "):void");
//								return ret;
//							}
//
//							@Override
//							public int entryNr() {
//								return 1;
//							}
//
//							@Override
//							public void add(String data) {
//							}
//						});
//						ret.add(new DataModelInterface() {
//
//							@Override
//							public String getIdentifier() {
//								return "members";
//							}
//
//							@Override
//							public ArrayList<String> getDataLine() {
//								ArrayList<String> ret = new ArrayList<String>();
//								ret.add("-randomNr:float");
//								ret.add("+name:String");
//								ret.add("#someProtectdShit:int");
//								return ret;
//							}
//
//							@Override
//							public int entryNr() {
//								return 1;
//							}
//
//							@Override
//							public void add(String data) {
//							}
//						});
//
//						return ret;
//					}
//
//					@Override
//					public void addDataString(int dataRowIndex, String data) throws Throwable {
//					}
//				});
//
//				ret.add(new EntityModelInterface() {
//
//					@Override
//					public void setSize(Dimension size) {
//					}
//
//					@Override
//					public void setName(String name) {
//					}
//
//					@Override
//					public void setLocation(Point location) {
//					}
//
//					@Override
//					public String getType() {
//						return "interface";
//					}
//
//					@Override
//					public Dimension getSize() {
//						return null;
//					}
//
//					@Override
//					public String getName() {
//						return "Stub";
//					}
//
//					@Override
//					public Point getLocation() {
//						return null;
//					}
//
//					@Override
//					public List<DataModelInterface> getDataRows() {
//						ArrayList<DataModelInterface> ret = new ArrayList<DataModelInterface>();
//						ret.add(new DataModelInterface() {
//
//							@Override
//							public String getIdentifier() {
//								return "methods";
//							}
//
//							@Override
//							public ArrayList<String> getDataLine() {
//								ArrayList<String> ret = new ArrayList<String>();
//								ret.add("+static main(args[]:String):void");
//								ret.add("+setMainWindow(assoctiatedMainWindow:MainWindowProxy,val:int" + "):void");
//								return ret;
//							}
//
//							@Override
//							public int entryNr() {
//								return 1;
//							}
//
//							@Override
//							public void add(String data) {
//							}
//						});
//						ret.add(new DataModelInterface() {
//
//							@Override
//							public String getIdentifier() {
//								return "members";
//							}
//
//							@Override
//							public ArrayList<String> getDataLine() {
//								ArrayList<String> ret = new ArrayList<String>();
//								ret.add("-randomNr:float");
//								ret.add("+name:String");
//								ret.add("#someProtectdShit:int");
//								return ret;
//							}
//
//							@Override
//							public int entryNr() {
//								return 1;
//							}
//
//							@Override
//							public void add(String data) {
//							}
//						});
//
//						return ret;
//					}
//
//					@Override
//					public void addDataString(int dataRowIndex, String data) throws Throwable {
//					}
//				});
//				
//				
//				ent1 = ret.get(0);
//				ent2 = ret.get(1);
//				ent3 = new EntityModelInterface() {
//					public void setSize(Dimension size) {
//					}
//					public void setName(String name) {
//					}
//					public void setLocation(Point location) {
//					}
//					public String getType() {
//						return "class";
//					}
//					public Dimension getSize() {
//						return null;
//					}
//					public String getName() {
//						return "SuperMain";
//					}
//					public Point getLocation() {
//						return null;
//					}
//					public List<DataModelInterface> getDataRows() {
//						return new ArrayList<>();
//					}
//					public void addDataString(int dataRowIndex, String data) throws Throwable {
//						
//					}
//				};
//				return ret;
//			}
//
//			@Override
//			public Dimension getSheetSize() {
//				return null;
//			}
//
//			@Override
//			public String getSheetName() {
//				return null;
//			}
//
//			@Override
//			public long getPluginSignature() {
//				return 0;
//			}
//
//			@Override
//			public File getDiskFile() {
//				return null;
//			}
//
//			@Override
//			public ArrayList<ConnectorModelInterface> getConnectors() {
//				ArrayList<ConnectorModelInterface> cmi = new ArrayList<ConnectorModelInterface>();
//				cmi.add(new ConnectorModelInterface() {
//
//					@Override
//					public void setStartText(String startText) {
//					}
//
//					@Override
//					public void setMidText(String midText) {
//					}
//
//					@Override
//					public void setEndText(String endText) {
//					}
//
//					@Override
//					public void setDrawnType(String type) {
//					}
//
//					@Override
//					public void removeSnap(Point snapLocation) {
//
//					}
//
//					@Override
//					public EntityModelInterface getTo() {
//						return Main.ent2;
//					}
//
//					@Override
//					public String getStartText() {
//						// TODO Auto-generated method stub
//						return "";
//					}
//
//					@Override
//					public ArrayList<Point> getSnaps() {
//						// TODO Auto-generated method stub
//						return new ArrayList<>();
//					}
//
//					@Override
//					public int getSnapCount() {
//						// TODO Auto-generated method stub
//						return 0;
//					}
//
//					@Override
//					public String getMidText() {
//						// TODO Auto-generated method stub
//						return "";
//					}
//
//					@Override
//					public EntityModelInterface getFrom() {
//						return Main.ent1;
//					}
//
//					@Override
//					public String getEndText() {
//						// TODO Auto-generated method stub
//						return "";
//					}
//
//					@Override
//					public String getDrawnType() {
//						return "Inheritance";
//					}
//
//					@Override
//					public void addSnap(int index, Point snap) {
//					}
//
//					@Override
//					public void addSnap(Point snap) {
//					}
//				});
//				cmi.add(new ConnectorModelInterface() {
//					public void setStartText(String startText) {
//					}
//					public void setMidText(String midText) {
//					}
//					public void setEndText(String endText) {
//					}
//					public void setDrawnType(String type) {
//					}
//					public void removeSnap(Point snapLocation) {
//					}
//					public EntityModelInterface getTo() {
//						return ent3;
//					}
//					public String getStartText() {
//						return null;
//					}
//					public ArrayList<Point> getSnaps() {
//						return new ArrayList<>();
//					}
//					public int getSnapCount() {
//						return 0;
//					}
//					public String getMidText() {
//						return "";
//					}
//					public EntityModelInterface getFrom() {
//						return ent1;
//					}
//					public String getEndText() {
//						return "";
//					}
//					public String getDrawnType() {
//						return "Inheritance";
//					}
//					public void addSnap(int index, Point snap) {
//					}
//					public void addSnap(Point snap) {
//					}
//				});
//	
//				
//				return cmi;
//			}
//
//			@Override
//			public boolean add(ConnectorModelInterface e) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//
//			@Override
//			public boolean add(EntityModelInterface e) {
//				// TODO Auto-generated method stub
//				return false;
//			}
//		};
//
//		// StubGenerator.generateCode(shM);
//		try {
//			List<String> data = JavaParser.getInstance().parseRoutine(shM);
//			for (String s : data) {
//				System.out.println(s);
//				System.out.println("---");
//			}
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//
//	}

}
