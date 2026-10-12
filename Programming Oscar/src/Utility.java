public class Utility {

    public static String[] stringtoArray (String text){
        String temp = text;

        if (temp == null || temp.trim().isEmpty()) {
            return null;
        }

        temp = temp.replace("-","").replaceAll("\\p{Punct}", "");
        String[] temp1 = temp.trim().split("\\s+");
        return temp1;
    }

    public static String[] distinct (String[] Array){

        if ( Array==null) return null;
        for (int i = 0; i < Array.length; i++) {
            if ( Array[i]==null) continue;
            for (int j = 0; j < Array.length; j++) {
                if ( j==i ) continue;

                if (Array[j]!=null&&Array[i].equals(Array[j])) {
                    Array[j] = null;
                }
            }
        }

        int count=0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]!=null) {
                count++;
            }
        }
        
        int j=0;
        String[] temp = new String[count];
        for (int i = 0; i < Array.length; i++) {
            if (Array[i] != null) {
                temp[j] = Array[i];
                j++; 
            }
        }
        Array = temp; 
        return Array;
    }

    public static int[] distinct (int[] Array){

        if ( Array==null) return null;
        for (int i = 0; i < Array.length; i++) {
            if ( Array[i] == 0) continue;
            for (int j = 0; j < Array.length; j++) {
                if ( j==i ) continue;

                if (Array[j]!=0 && Array[i] == Array[j]) {
                    Array[j] = 0;
                }
            }
        }

        int count=0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]!=0) {
                count++;
            }
        }
        
        int j=0;
        int[] temp = new int[count];
        for (int i = 0; i < Array.length; i++) {
            if (Array[i] != 0) {
                temp[j] = Array[i];
                j++; 
            }
        }
        Array = temp; 
        return Array;
    }

    public static int count (String[] Array1, String[] Array2 ){

        if (Array1 == null || Array2 == null) return 0;

        int count=0;
        for (int i = 0; i < Array1.length; i++) {
            for (int j = 0; j < Array2.length; j++) {
                if (Array1[i].equals(Array2[j])) {
                    count++;
                }
            }
        }
        return count;


    }

    public static int filter (String[] Array, String word ){

        if (Array == null || word == null) return 0;

        int count=0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i].equals(word)) {
                count++;
                }
        }
        return count;
    }

    public static int filter (int[] Array, int rollNo){

        if (Array == null || rollNo == 0) return 0;

        int count=0;
        for (int i = 0; i < Array.length; i++) {
            if (Array[i] == (rollNo)) {
                count++;
                }
        }
        return count;
    }

}


