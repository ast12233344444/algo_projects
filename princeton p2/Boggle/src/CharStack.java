public class CharStack {
    char[] stack ;
    int n;
    public CharStack(){
        stack = new char[100];
        n = 0;
    }

    public void put(char var){
        stack[n++] = var;
    }

    public char pop(){
        return stack[--n];
    }

    public char get(int index){
        return stack[index];
    }

    public boolean isempty(){
        return n ==0;
    }

    public String toString(){
        String s ="";
        for (int i =0;i<n;i++){
            s += stack[i];
        }
        return s;
    }
}
