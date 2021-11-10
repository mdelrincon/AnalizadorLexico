package codigo;
import codigo.ResultadoToken;
import java.util.ArrayList;

public class formacionTS {


static ArrayList<String> tablas= new ArrayList<String>(); //aqui iremos creando cada tabla  
static ArrayList<Integer> desplazamiento= new ArrayList<Integer>(); //aqui iremos guardando el desplazamiento de cada tabla 
	
 public static String CrearTabla(ArrayList<Token> listaToken) {
	String TablaP="TABLA PRINCIPAL #1:\n"; //la tabla principal
	int i=0;
	desplazamiento.add(0);
	tablas.add(TablaP);
	 while(i<listaToken.size()) {
			Token token=listaToken.get(i); //token que leemos
			int despl=desplazamiento.get(0); //desplazamiento de la tabla de simbolos
			switch(token.attrToken) {
				
				case "function":
					Token siguiente=listaToken.get(i+1);
					if(!(siguiente.attrToken.matches("id"))) {
							String identificador=listaToken.get(i+2).id;
							ArrayList<String> parametros=calcularParametros(i+4,listaToken); 
							TablaP= TablaP+ "* LEXEMA: '"+identificador+"'  (Funcion)\n"+ //añadimos el lexema con su nombre
									"ATRIBUTOS:\n"+
						            //"\t+ tipo: 'función'\n"+ //añadimos el tipo que es  
									"\t+ numParam: "+parametros.size()+"\n"; //añadimos el numero de parametros
							if(parametros.size()!=0) {
									int j=1;
									while(j<=parametros.size()) {
										TablaP=TablaP+"\t+ TipoParam0"+j+": '"+parametros.get(j-1)+"'\n"
											         +"\t+ ModoParam0"+j+": 1\n";
										j++;
									}
							}
							TablaP=TablaP+"\t+ TipoRetorno: '"+siguiente.attrToken+"'\n"+ //añadimos qué retorna	
									"\t+ EtiqFuncion: 'Et"+identificador+"01'\n\n";
							i=crearTablaFunction(identificador,i+3,listaToken); //creamos la tabla de la funcion enviando el nombre de esta, y donde empieza 
																				//sus parametros(si es que tiene) y la lista de tokens
							break;
					}
					else {
							String identificador=listaToken.get(i+1).id;
							ArrayList<String> parametros=calcularParametros(i+3,listaToken);
							TablaP= TablaP+ "* LEXEMA: '"+identificador+"'  (Funcion)\n"+ //añadimos el lexema con su nombre
									"ATRIBUTOS:\n"+
									//"\t+ tipo: 'función'\n"+ //añadimos el tipo que es  
									"\t+ numParam: "+parametros.size()+"\n"; //añadimos el numero de parametros
							if(parametros.size()!=0) {
									int j=1;
									while(j<=parametros.size()) {
										TablaP=TablaP+"\t+ TipoParam0"+j+": '"+parametros.get(j-1)+"'\n"
												+"\t+ ModoParam0"+j+": 1\n";
										j++;
									}
							}
							//a lo mejor hay que poner que no retorna nada 
							TablaP=TablaP+"\t+ EtiqFuncion: 'Et"+identificador+"01'\n\n";
							i=crearTablaFunction(identificador,i+2,listaToken); //creamos la tabla de la funcion 
							break;
					}
				
				case "int":
					Token siguienteID=listaToken.get(i+1);
					TablaP= TablaP+"* LEXEMA: '"+siguienteID.id+"'\n"+ //añadimos el lexema con su nombre
							"\t+ tipo: 'int'\n"+ //ponemos el tipo que es 
							"\t+ despl: "+despl+"\n\n";
							despl=despl+2; //aumentamos el desplazamiento que tiene un entero
							desplazamiento.set(0,despl); //lo metemos en el arraylist de los desplazamientos de cada tabla 
							i=i+2; //vamos al token siguiente al id 
							break;
				case "boolean":
					Token siguienteBO=listaToken.get(i+1);
					TablaP=TablaP+"* LEXEMA: '"+siguienteBO.id+"'\n"+ //añadimos el lexema con su nombre
							"\t+ tipo: 'boolean'\n"+ //ponemos el tipo que es 
							"\t+ despl: "+despl+"\n\n";
							despl=despl+1; //aumentamos el desplazamiento que tiene un entero
							desplazamiento.set(0,despl); //lo metemos en el arraylist de los desplazamientos de cada tabla 
							i=i+2; //vamos al token siguiente al id 
							break;
							
				case "string":
					Token siguienteCA=listaToken.get(i+1);
					TablaP=TablaP+"* LEXEMA: '"+siguienteCA.id+"'\n"+ //añadimos el lexema con su nombre
							"\t+ tipo: 'cadena'\n"+ //ponemos el tipo que es 
							"\t+ despl: "+despl+"\n\n";
							despl=despl+calculoDesplazamiento(i+1,listaToken); //aumentamos el desplazamiento que tiene un entero
							desplazamiento.set(0,despl); //lo metemos en el arraylist de los desplazamientos de cada tabla 
							i=i+2; //vamos al token siguiente al id
							break;
							
				default:
					i++;
					break;
			}
	}
TablaP=TablaP+"---------------------------------------------------\n";	 
for(int n=1;n<tablas.size();n++) { //juntamos todas las tablas en un mismo string
	TablaP=TablaP+tablas.get(n); 
	}
return TablaP; //devolvemos ya todas las tablas en un mismo string 
 }

 //devuelve en un ArrayList de Strings los parametros de una funcion
 public static ArrayList<String> calcularParametros(int p,ArrayList<Token> listaToken) {
	 ArrayList<String> parametros= new ArrayList<String>();
	 while(p<listaToken.size() && listaToken.get(p).attrToken!="pcerrado") {
		 String atributo=listaToken.get(p).attrToken;
		 if(atributo.matches("string") || atributo.matches("int") || atributo.matches("boolean")) {
			 parametros.add(atributo);
		 }
		 p++;
	 }
	 return parametros;
 }
 
 
 //crea las tablas function
 public static int crearTablaFunction(String id,int i,ArrayList<Token> listaToken) {
	 int llaves=1; //sera el contador de llaves para saber cuando la funcion ha acabado o si es una llave de un if, do while, etc
	 int primera=1; //se usara para no contar la primera llave de function
	 int numeroTablas=tablas.size();
	 String tablaFunction="TABLA FUNCION "+id+" #"+(numeroTablas+1)+":\n";
	 tablas.add(tablaFunction);
	 desplazamiento.add(0); //añadimos el desplazamiento de esta tabla al arrayList de desplazamientos
	 while(i<listaToken.size() && llaves>0) {
		 String atributo=listaToken.get(i).attrToken; //token que leemos
		 int despl=desplazamiento.get(numeroTablas);
		 switch(atributo) {
		 case "function":
				Token siguiente=listaToken.get(i+1);
				if(!(siguiente.attrToken.matches("id"))) {
						String identificador=listaToken.get(i+2).id;
						ArrayList<String> parametros=calcularParametros(i+4,listaToken); 
						tablaFunction= tablaFunction+ "* LEXEMA: '"+identificador+"' (Funcion)\n"+ //añadimos el lexema con su nombre
								"ATRIBUTOS:\n"+
					            //"\t+ tipo: 'función'\n"+ //añadimos el tipo que es  
								"\t+ numParam: "+parametros.size()+"\n"; //añadimos el numero de parametros
						if(parametros.size()!=0) {
								int j=1;
								while(j<=parametros.size()) {
									tablaFunction=tablaFunction+"\t+ TipoParam0"+j+": '"+parametros.get(j-1)+"'\n"
											+"\t+ ModoParam0"+j+": 1\n";
									j++;
								}
						}
						tablaFunction=tablaFunction+"\t+ TipoRetorno: '"+siguiente.attrToken+"'\n"+ //añadimos qué retorna	
								"\t+ EtiqFuncion: 'Et"+identificador+"01'\n\n";
						i=crearTablaFunction(identificador,i+3,listaToken); //creamos la tabla de la funcion enviando el nombre de esta, y donde empieza 
																			//sus parametros(si es que tiene) y la lista de tokens
						break;
				}
				else {
						String identificador=listaToken.get(i+1).id;
						ArrayList<String> parametros=calcularParametros(i+3,listaToken);
						tablaFunction= tablaFunction+ "* LEXEMA: '"+identificador+"' (Funcion)\n"+ //añadimos el lexema con su nombre
								"ATRIBUTOS:\n"+
								//"\t+ tipo: 'función'\n"+ //añadimos el tipo que es  
								"\t+ numParam: "+parametros.size()+"\n"; //añadimos el numero de parametros
						if(parametros.size()!=0) {
								int j=1;
								while(j<=parametros.size()) {
									tablaFunction=tablaFunction+"\t+ TipoParam0"+j+": '"+parametros.get(j-1)+"'\n"
											+"\t+ ModoParam0"+j+": 1\n";
									j++;
								}
						}
						//a lo mejor hay que poner que no retorna nada 
						tablaFunction=tablaFunction+"\t+ EtiqFuncion: 'Et"+identificador+"01'\n\n";
						i=crearTablaFunction(identificador,i+2,listaToken); //creamos la tabla de la funcion 
						break;
				}
			
			case "int":
				Token siguienteID=listaToken.get(i+1);
				tablaFunction=tablaFunction+ "* LEXEMA: '"+siguienteID.id+"'\n"+ //añadimos el lexema con su nombre
						"\t+ tipo: 'int'\n"+ //ponemos el tipo que es 
						"\t+ despl: "+despl+"\n\n";
						despl=despl+2; //aumentamos el desplazamiento que tiene un entero
						desplazamiento.set(numeroTablas,despl); //lo metemos en el arraylist de los desplazamientos de cada tabla 
						i=i+2; //vamos al token siguiente al id 
						break;
						
			case "boolean":
				Token siguienteBO=listaToken.get(i+1);
				tablaFunction=tablaFunction+"* LEXEMA: '"+siguienteBO.id+"'\n"+ //añadimos el lexema con su nombre
						"\t+ tipo: 'boolean'\n"+ //ponemos el tipo que es 
						"\t+ despl: "+despl+"\n\n";
						despl=despl+1; //aumentamos el desplazamiento que tiene un entero
						desplazamiento.set(numeroTablas,despl); //lo metemos en el arraylist de los desplazamientos de cada tabla 
						i=i+2; //vamos al token siguiente al id 
						break;
						
			case "string":
				Token siguienteCA=listaToken.get(i+1); //id del String
				tablaFunction=tablaFunction+"* LEXEMA: '"+siguienteCA.id+"'\n"+ //añadimos el lexema con su nombre
						"\t+ tipo: 'cadena'\n"+ //ponemos el tipo que es 
						"\t+ despl: "+despl+"\n\n";
						despl=despl+calculoDesplazamiento(i+1,listaToken); //aumentamos el desplazamiento que tiene el String
						desplazamiento.set(numeroTablas,despl); //lo metemos en el arraylist de los desplazamientos de cada tabla 
						i=i+2; //vamos al token siguiente al id
						break;
						
			case "cabierto":
				if (primera==0) { //para que no cuente la primera llave de function ya implementada
				llaves++;
				}
				else {
					primera--;
				}
				i++;
				break;
			
			case "ccerrado":
				llaves--;
				i++;
				break;
				
			default:
				i++;
				break;
		 	}
	 	}
   tablaFunction=tablaFunction+"---------------------------------------------------\n";	 
   tablas.set(numeroTablas, tablaFunction);
   return i;
  }
 
 //calcular el desplazamiento cuando se trata de una cadena 
 public static int calculoDesplazamiento(int i,ArrayList<Token> listaToken) {
	 int contador=0;
	 if(listaToken.get(i+1).attrToken=="asign") {
		 contador=listaToken.get(i+2).tipoToken.length()-2;
	 }
	 return contador;
 }
 
}
