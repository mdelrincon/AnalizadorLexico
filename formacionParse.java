package codigo;
import codigo.ResultadoToken;
import codigo.Token;
import java.util.ArrayList;

public class formacionParse {
	
static String parse="DESCENDENTE ";
static int numToken;
static ArrayList<Token> listaToken;
static String listaErrores;

public static String[] crearParse(ArrayList<Token> Tokens, String error) {
	listaToken=Tokens; //lista de tokens
	listaErrores=error;
	numToken=0; //miramos primer token
	//Empezamos por el axioma 
	S();
	if (listaErrores=="") {
    	listaErrores=listaErrores + "No se han encontrado errores en este código." + "\n";
    }
	String [] vuelta= {parse,listaErrores};
	return vuelta;
  }

public static void S() {
if(numToken>=listaToken.size()) {
	parse=parse+"3 ";
	return;
}
else {
	switch(listaToken.get(numToken).attrToken) {
	
	case "var":
		parse=parse+"1 ";
		A();
		S();
		return;
	
	case "while":
		parse=parse+"1 ";
		A();
		S();
		return;
	
	case "if":
		parse=parse+"1 ";
		A();
		S();
		return;
	
	case "input":
		parse=parse+"1 ";
		A();
		S();
		return;
		
	case "id":
		parse=parse+"1 ";
		A();
		S();
		return;
		
	case "return":
		parse=parse+"1 ";
		A();
		S();
		return;
		
	case "print":
		parse=parse+"1 ";
		A();
		S();
		return;
		
	case "function":
		parse=parse+"2 ";
		F();
		S();
		return;
		
	case "do":
		parse=parse+"1";
		A();
		S();
		return;
		
	default: 
		if(listaToken.get(numToken).attrToken.matches("id")) {
			listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

		}
		else {
			listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
		}
		break;
		//parse=parse+"3 ";
		//return;
			}
		}
	}

public static void A() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "while":
			
			parse=parse+"4 ";
			numToken++;
			Token siguiente=listaToken.get(numToken);
			if(siguiente.attrToken.matches("pabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+ "Error en analizador sintáctico: Debería haber un paréntesis abierto en lugar del token "
						+siguiente.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			E();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis cerrado en lugar del token "
						+siguiente.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("cabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber una llave abierta en lugar del token "
						+siguiente.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			Z();
			if(listaToken.get(numToken).attrToken.matches("ccerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber una llave cerrada en lugar del token "
						+siguiente.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "var":
			parse=parse+"5 ";
			numToken++;
			Token siguiente2=listaToken.get(numToken);
			V();
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente2.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente2.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "do":
			parse=parse+"6 ";
			D();
			return;
			
		case "if":
			parse=parse+"7 ";
			numToken++;
			Token siguiente3=listaToken.get(numToken);
			if(listaToken.get(numToken).attrToken.matches("pabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis abierto en lugar del token "
						+siguiente3.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			E();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis cerrado en lugar del token "
						+siguiente3.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			P();
			return;
			
		case "input":
			parse=parse+"8 ";
			numToken++;
			Token siguiente4=listaToken.get(numToken);
			if(listaToken.get(numToken).attrToken.matches("pabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis abierto en lugar del token "
						+siguiente4.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente4.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis cerrado en lugar del token "
						+siguiente4.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente4.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "print":
			parse=parse+"9 ";
			P();
			return;
			
		case "return":
			parse=parse+"9 ";
			P();
			return;
			
		case "id":
			parse=parse+"9 ";
			P();
			return;
			
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
			}
		}
	}

public static void B() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "pabierto":
			parse=parse+"32 ";
			E();
			W();
			return;
			
		case "cad":
			parse=parse+"32 ";
			E();
			W();
			return;
			
		case "entero":
			parse=parse+"32 ";
			E();
			W();
			return;
		case "id": 
			parse=parse+"32 ";
			E();
			W();
			return;
			
		case "pcerrado":
			parse=parse+"33 ";
			return;
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		   }//fin switch
		}
	}//fin de B

/*public static void Bc() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "entero":
			parse=parse+"14 ";
			numToken++;
			return;
			
		case "var":
			parse=parse+"15 ";
			numToken++;
			if(listaToken.get(numToken).attrToken.matches("int")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber habido un entero en lugar "
						+ "de "+listaToken.get(numToken).tipoToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber habido un identificador en lugar "
						+ "de "+listaToken.get(numToken).tipoToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("asign")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber habido un igual en lugar "
						+ "de "+listaToken.get(numToken).tipoToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("int")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber habido un entero en lugar "
						+ "de "+listaToken.get(numToken).tipoToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			
		default:
			listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken)+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			break;
		}//fin de switch
	  }//fin de else
	}//fin de Bc

public static void Bb() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "oplog":
			parse=parse+"17 ";
			numToken++;
			B();
			Bb();
			
		default:
			//listaErrores=listaErrores+"Error en analizador sintáctico: Token "+listaToken.get(numToken)+" no aceptado aquí";
			//break;
			parse=parse+"16 ";
			return;
		}//fin de switch
	}//fin de else
  }//fin de Bb */

public static void V() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "int":
			parse=parse+"11 ";
			numToken++;
			return;
			
		case "string":
			parse=parse+"12 ";
			numToken++;
			return;
			
		case "boolean":
			parse=parse+"13 ";
			numToken++;
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
			//parse=parse+"21 ";
			//return;
		}//fin de switch
	}//fin de else
 }//fin de V

public static void W() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "coma":
			parse=parse+"34 ";
			numToken++;
			E();
			W();
			return;
			
		case "pcerrado":
			parse=parse+"35 ";
			return;
			
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin de switch
	}//fin de else
 }//fin de W

public static void R() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "entero":
			parse=parse+"44 ";
			numToken++;
			return;
			
		case "cad":
			parse=parse+"45 ";
			numToken++;
			return;
			
		case "pabierto":
			parse=parse+"46 ";
			numToken++;
			Token siguiente6=listaToken.get(numToken);
			E();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis cerrado en lugar del token "
						+siguiente6.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "id":
			parse=parse+"43 ";
			numToken++;
			Ra();
			return;
			
		default:	
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin de switch
	}//fin de else
  }//fin de R

public static void Ra() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "decremento":
			parse=parse+"47";
			numToken++;
			Token siguiente21=listaToken.get(numToken);
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente21.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
		
		case "pabierto":
			parse=parse+"48 ";
			numToken++;
			Token siguiente7=listaToken.get(numToken);
			B();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis cerrado en lugar del token "
						+siguiente7.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "pcerrado":
			parse=parse+"49 ";
			return;
			
		case "oparit":
			parse=parse+"49 ";
			return;
			
		case "coma": 
			parse=parse+"49 ";
			return;
			
		case "pcoma":
			parse=parse+"49 ";
			return;
			
		case "oprel":
			parse=parse+"49 ";
			return;
			
		case "asign":
			parse=parse+"49 ";
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}			break;
		}//fin switch
	}//fin else
 }//fin Ra

public static void D() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "do":
			parse=parse+"10 ";
			numToken++;
			Token siguiente8=listaToken.get(numToken);
			if(listaToken.get(numToken).attrToken.matches("cabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber una llave abierta en lugar del token "
						+siguiente8.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			Z();
			if(listaToken.get(numToken).attrToken.matches("ccerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber una llave cerrada en lugar del token "
						+siguiente8.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("while")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un while en lugar del token "
						+siguiente8.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis abierto en lugar del token "
						+siguiente8.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			E();			
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un paréntesis cerrado en lugar del token "
						+siguiente8.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente8.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin de switch
	}//fin de else
  }//fin de D

public static void E() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "entero":
			parse=parse+"36 ";
			C();
			Eb();
			return;
			
		case "cad":
			parse=parse+"36 ";
			C();
			Eb();
			return;
			
		case "id":
			parse=parse+"36 ";
			C();
			Eb();
			return;
		
		case "pabierto":
			parse=parse+"36 ";
			C();
			Eb();
			return;
			
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin de switch
	}//fin de else
  }//fin de E

public static void Eb() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "oprel": 
			parse=parse+"38 ";
			numToken++;
			E();
			return;
			
		case "asign":
			parse=parse+"37 ";
			numToken++;
			E();
			return;
			
		case "pcerrado":
			parse=parse+"39 ";
			return;
			
		case "coma":
			parse=parse+"39 ";
			return;
			
		case "pcoma":
			parse=parse+"39 ";
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
			//parse=parse+"31 ";
			//return;
		}//fin del switch
	}//fin del else
 }//fin de Eb

public static void P() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "print":
			parse=parse+"16 ";
			numToken++;
			Token siguiente9=listaToken.get(numToken);
			if(listaToken.get(numToken).attrToken.matches("pabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis abierto en lugar del token "
						+siguiente9.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			E();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis cerrado en lugar del token "
						+siguiente9.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente9.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "id":
			parse=parse+"14 ";
			numToken++;
			Pb();
			return;
			
		case "return":
			parse=parse+"15 ";
			numToken++;
			Token siguiente10=listaToken.get(numToken);
			M();
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente10.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch
	}//fin else 
  }//fin P

public static void Pb() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "pabierto":
			numToken++;
			Token siguiente11=listaToken.get(numToken);
			parse=parse+"19 ";
			B();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis cerrado en lugar del token "
						+siguiente11.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente11.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "asign":
			numToken++;
			Token siguiente12=listaToken.get(numToken);
			parse=parse+"17 ";
			E();
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente12.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "oprel":
			parse=parse+"18 ";
			numToken++;
			Token siguiente13=listaToken.get(numToken);
			E();
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente13.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		case "decremento":
			parse=parse+"20 ";
			numToken++;
			Token siguiente20=listaToken.get(numToken);
			if(listaToken.get(numToken).attrToken.matches("pcoma")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un punto y coma en lugar del token "
						+siguiente20.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch
	}//fin else
 }//fin Pb

public static void G() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "coma":
			parse=parse+"30 ";
			numToken++;
			Token siguiente14=listaToken.get(numToken);
			V();
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente14.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			G();
			return;
		
		case "pcerrado":
			parse=parse+"31 ";
			return;
			
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch
	}//fin else
 }//fin G

public static void M() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
			
		case "id":
			parse=parse+"21 ";
			E();
			return;
			
		case "pabierto":
			parse=parse+"21 ";
			E();
			return;
			
		case "cad":
			parse=parse+"21 ";
			E();
			return;
			
		case "entero":
			parse=parse+"21 ";
			E();
			return;
			
		case "pcoma":
			parse=parse+"22 ";
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch
	 }//fin else
 }//fin M

/*public static void Mb() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "+":
			parse=parse+"40 ";
			numToken++;
			T();
			Mb();
			return;
			
		default: 
			parse=parse+"39 ";
			return;
		}//fin switch
	}//fin else
 }//fin Mb*/

public static void T() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "boolean":
			parse=parse+"28 ";
			numToken++;
			Token siguiente15=listaToken.get(numToken);
			V();
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente15.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			G();
			return;
			
		case "int": 
			parse=parse+"28 ";
			numToken++;
			Token siguiente16=listaToken.get(numToken);
			V();
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente16.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			G();
			return;
			
		case "string": 
			parse=parse+"28 ";
			numToken++;
			Token siguiente17=listaToken.get(numToken);
			V();
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente17.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			G();
			return;
			
		case "pcerrado":
			parse=parse+"29 ";
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch
	}//fin else
 }//fin T

public static void F() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "function": 
			parse=parse+"25 ";
			numToken++;
			Token siguiente18=listaToken.get(numToken);
			Fp();
			if(listaToken.get(numToken).attrToken.matches("id")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un identificador en lugar del token "
						+siguiente18.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("pabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis abierto en lugar del token "
						+siguiente18.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			T();
			if(listaToken.get(numToken).attrToken.matches("pcerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber un parentesis cerrado en lugar del token "
						+siguiente18.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			if(listaToken.get(numToken).attrToken.matches("cabierto")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber una llave abierta en lugar del token "
						+siguiente18.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			Z();
			if(listaToken.get(numToken).attrToken.matches("ccerrado")) {
				numToken++;
			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Debería haber una llave cerrada en lugar del token "
						+siguiente18.attrToken+" en la línea "+listaToken.get(numToken).lineaTexto+".\n";
				return;
			}
			return;
			
		default:	
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch 
	}//fin else 
  }//fin F

public static void Fp() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "int":
			parse=parse+"26 ";
			V();
			return;
			
		case "string":
			parse=parse+"26 ";
			V();
			return;
			
		case "boolean":
			parse=parse+"26 ";
			V();
			return;
			
		case "id":
			parse=parse+"27 ";
			return;
			
		default:
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;
		}//fin switch
	 }//fin else
 }//fin Fp

public static void C() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "pabierto":
			parse=parse+"40 ";
			R();
			Cb();
			return;
			
		case "cad":
			parse=parse+"40 ";
			R();
			Cb();
			return;
			
		case "entero":
			parse=parse+"40 ";
			R();
			Cb();
			return;
			
		case "id":
			parse=parse+"40 ";
			R();
			Cb();
			return;
			
			
		default: 
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;			
		}//fin switch
	}//fin else
 }//fin C

public static void Cb() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "oparit":
			parse=parse+"41 ";
			numToken++;
			C();
			return;
			
		case "pcerrado":
			parse=parse+"42 ";
			return;
			
		case "coma":
			parse=parse+"42 ";
			return;
			
		case "pcoma":
			parse=parse+"42 ";
			return;
			
		case "oprel":
			parse=parse+"42 ";
			return;
			
		case "asign":
			parse=parse+"42 ";
			return;
			
		default:	
			if(listaToken.get(numToken).attrToken.matches("id")) {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

			}
			else {
				listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
			}
			break;	
		}//fin switch
	}//fin else
 }//fin Cb

public static void Z() {
	if(numToken>=listaToken.size()) {
		listaErrores=listaErrores+ "Error en analizador sintáctico: No se encuentran mas tokens\n";
	}
	else {
		switch(listaToken.get(numToken).attrToken) {
		case "while":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "var":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "do":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "if":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "input":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "print":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "return":
			parse=parse+"23 ";
			A();
			Z();
			return;
			
		case "id":
			parse=parse+"23 ";
			A();
			Z();
			return;
		
		case "ccerrado":
			parse=parse+"24 ";
			return;
			
		 default:
			 if(listaToken.get(numToken).attrToken.matches("id")) {
					listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).id+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			

				}
				else {
					listaErrores=listaErrores+"Error en analizador sintáctico: Token "+ listaToken.get(numToken).attrToken+ " en la línea "+listaToken.get(numToken).lineaTexto+".\n";			
				}
			 break;	
		}//fin switch
	 }//fin else
 }//fin Z 
}
