/* 
Algorithms-rectangles:
1. Given a two-dimensional array N * N, which contains several rectangles.
2. Different rectangles do not touch or overlap.
3. Inside the rectangle is full 1.
4. In the array:
4.1) a [i, j] = 1 if the element (i, j) belongs to some rectangle
4.2) a [i, j] = 0, otherwise
5. getRectangleCount must return the number of rectangles.
*/
public class FindingRectangles  {
    public static void main(String[] args) {
        byte[][] a = new byte[][]{
                {1, 1, 0, 1},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        int count = getRectangleCount(a);
        System.out.println("count = " + count + ". Должно быть 2");
    }
    /*public static int getRectangleCount(byte[][] a) {
        int count = 0, tmp = -1, jt = a[0].length;
        boolean findRect = false;
        while (tmp != count) { //если счётчик увеличился за проход по матрице - снова делаем проход по матрице
            tmp = count;
            outerLoop:
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++) {
                    if (a[i][j] == 1 && !findRect) { //случай, когда встретили прямоугольник
                        count++;
                        jt = j; //запоминаем столбец, в котором начался прямоугольник
                        findRect = true;
                        a[i][j] = 0;
                    }
                    else if (a[i][j] == 1 && findRect) {//обнуляем, чтобы не мешался
                        a[i][j] = 0;
                    }
                    else if (a[i][j] == 0 && findRect && j > jt) { //если элемент справа от прямоугольника равен 0 - идем на след строку
                        break;
                    }
                    else if (a[i][j] == 0 && findRect && j == jt) { //если элемент под прямоугольник равен 0 - он закончился
                        break outerLoop; //выходим из внешнего цикла
                    }
                }
            }
            findRect = false;
        }
        return count;
    }*/

    public static int getRectangleCount(byte[][] a) {
        int count = 0, //count - current number of rectangles;
                temp = -1, //temp -- count in previous iteration;
                leftBorder = a[0].length; //   leftBorder-- number of the column in which rectangle starts;
        boolean isRectFound = false;
        while (count != temp) {
            temp = count;
            outerBlock:
            for (int i = 0; i < a.length; i++) {  //Iteration through raws (OY)
                for (int j = 0; j < a[0].length; j++) {
                    if ((a[i][j] == 1) && !isRectFound) { //if meet the left upper corner of the rectangle
                        count++;
                        isRectFound = true;
                        leftBorder = j;
                        a[i][j] = 0;
                    }
                    else if ((a[i][j] == 1) && isRectFound){ // if meet the part of the corner
                        a[i][j] = 0;
                    }
                    else if ((a[i][j] == 0) && j > leftBorder && isRectFound){ //if the rectangle on this line ends
                        break; // go to the next line
                    }
                    else if(a[i][j] == 0 && j == leftBorder && isRectFound){ //if rectangle ends (there is nothing underneath)

                      //isRectFound = false; // Why if here then not right result? Because if this statement never reached by loop ends by itself the sytem becomes fucked and the answer is always 1.
                        break outerBlock; // start from the very beginning (because the end of the rectangle is reached).
                    }

                }
            }
            isRectFound = false;
        }
        return count;
    }

}
