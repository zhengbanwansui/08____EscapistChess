package Game;

public class String3 {
    public String str1, str2, str3;
    public String3(String str1,String str2,String str3) {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
    }
    public void setValue(String str1,String str2,String str3) {
        this.str1 = str1;
        this.str2 = str2;
        this.str3 = str3;
    }
    public void show(){
        System.out.println(str1 + " " + str2 + " " + str3);
    }
}
