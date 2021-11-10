package codigo;
import codigo.Token;
import codigo.Token.Accion;
import codigo.formacionTS;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ResultadoToken {

	public static void CrearArchivo(String resultado, String errores, String tabla, String parse, String gramatica) {
		//crear tokens 
		try {
	            String ruta = "C:\\Users\\sergi\\Documents\\UNIVERSIDAD\\Tercer año\\Procesador de Lenguajes\\Práctica\\Tokens.txt";
	            File file = new File(ruta);
	            // Si el archivo no existe es creado
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(resultado);
	            bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("Problema al crear archivo de Tokens");
	        }
		 System.out.print("Archivo Tokens creado\n");
		 //crear errores
		 try {
	            String ruta = "C:\\Users\\sergi\\Documents\\UNIVERSIDAD\\Tercer año\\Procesador de Lenguajes\\Práctica\\Errores.txt";
	            File file = new File(ruta);
	            // Si el archivo no existe es creado
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	            if (errores=="") {
	            	errores=errores + "No se han encontrado errores en este código." + "\n";
	            }
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(errores);
	            bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("Problema al crear archivo de Errores");
	        }
		 System.out.print("Archivo errores creado\n");
		//crear tabla
		 try {
			 String ruta = "C:\\Users\\sergi\\Documents\\UNIVERSIDAD\\Tercer año\\Procesador de Lenguajes\\Práctica\\TablaSimbolos.txt";
	            File file = new File(ruta);
	            // Si el archivo no existe es creado
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	            if (tabla=="") {
	            	tabla="No se ha podido crear tabla" + "\n";
	            }
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(tabla);
	            bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("Problema al crear archivo de TablaSimbolos");
	        }
		 System.out.print("Archivo TablaSimbolos creado\n");
		//crear gramatica
		 try {
			 String ruta = "C:\\Users\\sergi\\Documents\\UNIVERSIDAD\\Tercer año\\Procesador de Lenguajes\\Práctica\\Gramatica.txt";
	            File file = new File(ruta);
	            // Si el archivo no existe es creado
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	            if (gramatica=="") {
	            	gramatica="No se ha podido crear la gramatica" + "\n";
	            }
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(gramatica);
	            bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("Problema al crear archivo de la Gramatica");
	        }
		 System.out.print("Archivo Gramatica creado\n");
		 //crear parse 
		 try {
			 String ruta = "C:\\Users\\sergi\\Documents\\UNIVERSIDAD\\Tercer año\\Procesador de Lenguajes\\Práctica\\Parse.txt";
	            File file = new File(ruta);
	            // Si el archivo no existe es creado
	            if (!file.exists()) {
	                file.createNewFile();
	            }
	            if (parse=="") {
	            	parse="No se ha podido crear parse" + "\n";
	            }
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(parse);
	            bw.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.print("Problema al crear archivo de Parse");
	        }
		 System.out.print("Archivo Parse creado\n");
	}
	
	public static void main(String ars[]) throws IOException {
		JFileChooser chooser = new JFileChooser();
    	chooser.showOpenDialog(null);
    	    Reader lector = new BufferedReader(new FileReader(chooser.getSelectedFile()));
    	    String resultado = "";
    	    String errores="";
    	    int estado = 0;
    	    ArrayList<Token> listaToken= new ArrayList<Token>(); //aqui guardaremos los tokens para la tabla de simbolos 
    		Accion accion;
    		String concatenacion = "";
    		int lineaTexto=1;
    		int linea=0;
    		int c;
    		int ptrid=1;
    		int contadorCadena=0;
    		while((c = lector.read()) != -1) {
    			char character = (char) c; //Leemos un caracter
    			if(character!= ' ' && character != '	' && character != '\b' && character != '\t' && character != '\n'
    					&& character != '/' && (Character.toString(character).matches("[a-zA-Z]")==false) 
    					&& (Character.toString(character).matches("[0-9]")==false) && character != '_'
    					&& character != '{' && character != '}' && character != '(' && character != ')' 
    					&& character != ';' && character != ',' && character != 39 && character != '+' && character != '&'
    					&& character != '\r' && character != '-' && estado!= 6 && character!= '=' && character!= '<') {
    					errores=errores + "Error, no se reconoce el caracter \"" + Character.toString(character) + "\" en la línea " 
    					+ lineaTexto+"." + "\n";
    					continue;
    				}
    			if(character == '\n') {
    				lineaTexto++;
    			}
    			accion = Token.estado(estado, character);
    			estado = accion.estado;
    			//Accion: leer 0
    			//        No leer 1
    			//        Leer y Concatenar 2
    			//        Comprobar palRes 3
    			//        GenToken Numero 4
    			//        GenToken Cadena 5
    			//		  GenToken ID 6
    			// 		  Leemos y GenToken 7
    			//		  No leemos y GenToken 8
    			//        Error al no reconcer el -. No lee 9
    			
    			while(accion.accion != 0){
    				if (accion.accion == 1){
    					accion = Token.estado(estado, character); 
    					estado = accion.estado;
    					continue;
    				}
    				if(accion.accion==2) {
    					concatenacion=concatenacion + character;
    					contadorCadena++;
    					break;
    				}
    				if (accion.accion == 3){
    					if(concatenacion.matches("boolean|var|int|if|while|do|function|string|return|input|print|")){
    						Token PRes= new Token("",concatenacion,linea,"",lineaTexto);
    						listaToken.add(PRes);
    						linea++;
    						resultado=escribirToken(resultado,PRes); //Palabra reservada
    					}else{
    						boolean esta=false;
    						int i=0;
    						while(!esta && i<listaToken.size()) { //tenemos que comprobar que el ID no está ya creado
    							if(concatenacion.matches(listaToken.get(i).id)) {
    								esta=true;
    							}
    							i++;
    						}
    						if(!esta) { //si no se encuentra ya creado
    							Token Id= new Token(Integer.toString(ptrid),"id",linea,concatenacion,lineaTexto);
    							listaToken.add(Id);
    							linea++;
    							ptrid++;
        						resultado=escribirToken(resultado,Id); //Generamos ID	
    						}
    						else {
    							Token Id= new Token(/*Integer.toString(i)*/ listaToken.get(i-1).tipoToken,"id",linea,concatenacion,lineaTexto);
    							listaToken.add(Id);
    							linea++;
        						resultado=escribirToken(resultado,Id); //Generamos ID
    						}
    					}
    					concatenacion=""; //Reset
    					//Leemos caracter actual:
    					accion = Token.estado(estado, character); 
    					estado = accion.estado;
    					continue;
    				}
    				
    				if(accion.accion==4) {
    					int numero=Integer.parseInt(concatenacion);
    					if (numero<=32767){
    						Token Entero=new Token(concatenacion,"entero",linea,"",lineaTexto);
    						linea++;
    						listaToken.add(Entero);
    						resultado=escribirToken(resultado,Entero);
    					}
    					else {
    						errores=errores+"Error, numero " + numero+ " fuera de rango en la línea "+lineaTexto+".\n";
    					}
    					/*else {
    						Errores.escribirError("El numero esta fuera del rango");
    					}*/
    					concatenacion="";
    					accion=Token.estado(estado, character);
    					estado=accion.estado;
    					continue;
    				}
    				
    				if (accion.accion==5) {
    					if(contadorCadena<=64) {
    						Token cadena=new Token("\""+concatenacion+"\"","cad",linea,"",lineaTexto);
    						linea++;
    						listaToken.add(cadena);
    						resultado=escribirToken(resultado,cadena);
    					}
    					else{
    						errores=errores+"Error, cadena fuera de rango en la línea "+lineaTexto+".\n";
    					}
    					concatenacion="";
    					contadorCadena=0;
    					break;
    				}
    				
    				if(accion.accion==6) {
    					boolean esta=false;
						int i=0;
						while(!esta && i<listaToken.size()) { //tenemos que comprobar que el ID no está ya creado
							if(concatenacion.matches(listaToken.get(i).id)) {
								esta=true;
							}
							i++;
						}
						if(!esta) { //si no se encuentra ya creado
							Token Id= new Token(Integer.toString(ptrid),"id",linea,concatenacion,lineaTexto);
							listaToken.add(Id);
							linea++;
							ptrid++;
    						resultado=escribirToken(resultado,Id); //Generamos ID	
						}
						else {
							Token Id= new Token(/*Integer.toString(i)*/ listaToken.get(i-1).tipoToken,"id",linea,concatenacion,lineaTexto);
							listaToken.add(Id);
							linea++;
    						resultado=escribirToken(resultado,Id); //Generamos ID
						}
    					concatenacion="";
    				    accion=Token.estado(estado, character);
    				    estado=accion.estado;
    				    continue;
    				}
    				if(accion.accion==7) {
    					String caracter="";
    					if(character==';') {
    					 caracter="pcoma";	
    					}
    					if(character==',') {
    						caracter="coma";
    					}
    					if(character=='+') {
    						caracter="oparit";
    					}
    					if(character=='&') {
    						caracter="oplog";
    					}
    					if(character=='-') {
    						caracter="decremento";
    					}
    					if(character=='(') {
    						caracter="pabierto";
    					}
    					if(character==')') {
    						caracter="pcerrado";
    					}
    					if(character=='{') {
    						caracter="cabierto";
    					}
    					if(character=='}') {
    						caracter="ccerrado";
    					}
    					if(character=='=') {
    						caracter="asign";
    					}
    					if(character=='<') {
    						caracter="oprel";
    					}
    					Token gen=new Token("",caracter,linea,"",lineaTexto);
    					linea++;
    					listaToken.add(gen);
    					resultado=escribirToken(resultado,gen);
    					concatenacion="";
    					break;
    				}
    				if(accion.accion==8) {
    					String caracter="";
    					if(character==';') {
    					 caracter="pcoma";	
    					}
    					if(character==',') {
    						caracter="coma";
    					}
    					if(character=='+') {
    						caracter="oparit";
    					}
    					if(character=='&') {
    						caracter="oplog";
    					}
    					//if(character=='-') {
    						//caracter="decremento";
    					//}
    					if(character=='(') {
    						caracter="pabierto";
    					}
    					if(character==')') {
    						caracter="pcerrado";
    					}
    					if(character=='{') {
    						caracter="cabierto";
    					}
    					if(character=='}') {
    						caracter="ccerrado";
    					}
    					if(character=='=') {
    						caracter="asign";
    					}
    					if(character=='<') {
    						caracter="oprel";
    					}
    					Token gen=new Token("",caracter,linea,"",lineaTexto);
    					linea++;
    					listaToken.add(gen);
    					resultado=escribirToken(resultado,gen);
    					concatenacion="";
    					accion=Token.estado(estado, character);
    					estado=accion.estado;
    					continue;
    				}
    				//if(accion.accion==8) {
    					//concatenacion="";
    					//break;
    				//}
    				if(accion.accion==9) {
    					errores=errores+"No se reconoce el caracter \"-\""+" en la línea "+lineaTexto+"\n";
    					concatenacion="";
    					accion=Token.estado(estado, character);
    					estado=accion.estado;
    					continue;
    				}
    			}
    		}
    		String tabla= formacionTS.CrearTabla(listaToken); //creamos la tabla
    		String gramatica=crearGramatica();
    		String[] vuelta=formacionParse.crearParse(listaToken,errores); //creamos el parse 
    		String parse=vuelta[0];
    		errores=errores+vuelta[1];
    		CrearArchivo(resultado,errores,tabla,parse,gramatica);
 }
   

public static String escribirToken(String resultado,Token token) {
    			resultado=resultado+"<"+token.attrToken+","+token.tipoToken+">"+'\n';
    			return resultado;
}
public static String crearGramatica() {
	String gramatica = "Terminales = { var id ; if =  {  } (  ) + <  -- , &&  function int input string boolean return print do entero cadena while }\r\n" + 
			"NoTerminales = { S A D V P Pb M Z F Fp T G B W E Eb C Cb R Ra }\r\n" + 
			"Axioma = S\r\n" + 
			"Producciones = {\r\n"
			+ "S -> A S \r\n" + 
			"S -> F S \r\n" + 
			"S -> lambda \r\n" + 
			"A -> while ( E ) { Z }\r\n" + 
			"A -> var V id ;\r\n" + 
			"A -> D \r\n" + 
			"A -> if ( E ) P\r\n" + 
			"A -> input ( id ) ;\r\n" + 
			"A -> P \r\n" + 
			"D -> do { Z } while ( E ) ;\r\n" + 
			"V -> int \r\n" + 
			"V -> string \r\n" + 
			"V -> boolean \r\n" + 
			"P -> id Pb \r\n" + 
			"P -> return M ; \r\n" + 
			"P -> print ( E ) ;\r\n" + 
			"Pb -> = E ;\r\n" + 
			"Pb -> < E ;\r\n" + 
			"Pb -> ( B ) ;\r\n" + 
			"Pb -> -- ;\r\n" +
			"M -> E\r\n" + 
			"M -> lambda \r\n" + 
			"Z -> A Z \r\n" + 
			"Z -> lambda \r\n" + 
			"F -> function Fp id ( T ) { Z }\r\n" + 
			"Fp -> V \r\n" + 
			"Fp -> lambda \r\n" + 
			"T -> V id G\r\n" + 
			"T -> lambda \r\n" + 
			"G -> , V id G \r\n" + 
			"G -> lambda \r\n" + 
			"B -> E W\r\n" + 
			"B -> lambda \r\n" + 
			"W -> , E W \r\n" + 
			"W -> lambda \r\n" + 
			"E -> C Eb \r\n" + 
			"Eb -> = E\r\n" + 
			"Eb -> < E \r\n" + 
			"Eb -> lambda \r\n" + 
			"C -> R Cb\r\n" + 
			"Cb -> + C\r\n" + 
			"Cb -> lambda\r\n" + 
			"R -> id Ra\r\n" + 
			"R -> entero \r\n" + 
			"R -> cadena \r\n" +  
			"R -> ( E )\r\n" + 
			"Ra -> -- ;\r\n" + 
			"Ra -> ( B ) \r\n" + 
			"Ra -> lambda \r\n" + 
			"}\r\n" + 
			"";
	return gramatica;
}
}