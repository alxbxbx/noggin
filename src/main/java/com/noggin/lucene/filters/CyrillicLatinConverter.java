package com.noggin.lucene.filters;

public class CyrillicLatinConverter {
	public static String cir2lat(String text) {

		String ret = "";
		for (int i = 0; i < text.length(); i++) {
			char c=text.charAt(i);
			switch(c){
				case '\u0430': ret+="a"; break;
				case '\u0431': ret+="b"; break;
				case '\u0446': ret+="c"; break;
				case '\u0434': ret+="d"; break;
				case '\u0435': ret+="e"; break;
				case '\u0444': ret+="f"; break;
				case '\u0433': ret+="g"; break;
				case '\u0445': ret+="h"; break;
				case '\u0438': ret+="i"; break;
				case '\u0458': ret+="j"; break;
				case '\u043A': ret+="k"; break;
				case '\u043B': ret+="l"; break;
				case '\u043C': ret+="m"; break;
				case '\u043D': ret+="n"; break;
				case '\u043E': ret+="o"; break;
				case '\u043F': ret+="p"; break;
				case '\u0440': ret+="r"; break;
				case '\u0441': ret+="s"; break;
				case '\u0442': ret+="t"; break;
				case '\u0443': ret+="u"; break;
				case '\u0432': ret+="v"; break;
				case '\u0437': ret+="z"; break;
				case '\u0410': ret+="A"; break;
				case '\u0411': ret+="B"; break;
				case '\u0426': ret+="C"; break;
				case '\u0414': ret+="D"; break;
				case '\u0415': ret+="E"; break;
				case '\u0424': ret+="F"; break;
				case '\u0413': ret+="G"; break;
				case '\u0425': ret+="H"; break;
				case '\u0418': ret+="I"; break;
				case '\u0408': ret+="J"; break;
				case '\u041A': ret+="K"; break;
				case '\u041B': ret+="L"; break;
				case '\u041C': ret+="M"; break;
				case '\u041D': ret+="N"; break;
				case '\u041E': ret+="O"; break;
				case '\u041F': ret+="P"; break;
				case '\u0420': ret+="R"; break;
				case '\u0421': ret+="S"; break;
				case '\u0422': ret+="T"; break;
				case '\u0423': ret+="U"; break;
				case '\u0412': ret+="V"; break;
				case '\u0417': ret+="Z"; break;
				case '\u045B': ret+="\u0107"; break;
				case '\u0447': ret+="\u010D"; break;
				case '\u0452': ret+="\u0111"; break;
				case '\u0448': ret+="\u0161"; break;
				case '\u0436': ret+="\u017E"; break;
				case '\u040B': ret+="\u0106"; break;
				case '\u0427': ret+="\u010C"; break;
				case '\u0402': ret+="\u0110"; break;
				case '\u0428': ret+="\u0160"; break;
				case '\u0416': ret+="\u017D"; break;
				case '\u045F': ret+="d\u017E";break;
				case '\u0459': ret+="lj";break;
				case '\u045A': ret+="nj";break;
				case '\u040F': ret+="D\u017E";break;
				case '\u0409': ret+="Lj";break;
				case '\u040A': ret+="Nj";break;
				default : ret+=c;
			}
		}
		return ret;
	}

}
