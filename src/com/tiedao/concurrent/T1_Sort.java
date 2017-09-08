package com.tiedao.concurrent;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Arrays;

/**
 * @Authour: Mr灬LandLord
 * @PackageName: com.tiedao.concurrent
 * @Date: 2017/8/22
 * @Time: 20:54
 * @Description:
 */
public class T1_Sort {


    /*                                                       错错错错错
    public static void sort1(int[] s){
        int length = s.length;
        for (int i = 0; i < length; i++){
        //---min用来记下标
            int min = s[i];i
            for (int j = i+1; j < length; j++){
                if (s[j] < min){
                    min = s[j];
                }
            }
            int temp = 0;
            temp = s[i];
            s[i] = min;
            min = temp;
        }
    }*/

public static void print(String name, int [] target){
    System.out.println(name + "排序：");
    for (int i : target){
        System.out.print(i+" ");
    }
}

    public static void select(int[] s){
        int length = s.length;
        for (int i = 0; i < length; i++){
            int min = i;
            int temp = s[i];
            for (int j = i+1; j < length; j++){
                if (s[j] < s[min]){
                    min = j;
                }
            }
            s[i] = s[min];
            s[min] = temp;
        }
        print("选择",s);
    }


    /*
    public static void sort2(int[] s){
        int length = s.length;
        for (int i = 1; i < length; i++){
            int temp = s[i];
            for (int j = i; j >= 0; j--){               //错错错错错
                if (s[j] > s[i]){
                    temp = s[j];
                    s[j] = s[i+1];
                    s[i+1] = temp;
                }
            }
        }
    }*/
    //还需了解插入排序其它变种
    public static void insert1(int[] s){
        int length = s.length;
        int temp;
        for (int i = 1; i < length; i++){
            for (int j = i; j > 0; j--){
                if (s[j] < s[j-1]){
                    temp = s[j];
                    s[j] = s[j-1];
                    s[j-1] = temp;
                }
            }
        }
        print("插入",s);
    }
    //好一点的插入排序
    public static void insert2(int[] s){
        int length = s.length;
        int i,j;
        for ( i = 1; i < length; i++){
            int max = s[i];
            for ( j = i-1; j >= 0; j--){
                if (max < s[j]){
                    s[j+1] = s[j];
                }else {
                    break;
                }
            }
            s[j+1] = max;
        }
        print("插入",s);
    }
    //冒泡
    public static void bubble(int []src ){
        int i, j, length = src.length;
        int temp;
        for (i = length; i > 0; i--){
            for (j = 1; j < i; j++){          //错错错错错 把'j = 1'写成'j = 0'出现数组越界
                 if (src[j] < src[j-1]){
                    temp = src[j];
                    src[j] = src[j-1];
                    src[j-1] = temp;
                }
            }
        }
        print("冒泡", src);
    }


    //希尔排序
    public static void shell(int [] src){
        int i, j, h, length = src.length, temp;
        for (h = length / 2; h > 0; h /= 2){
            for (i = h; i < length; i++){
                for (j = i - h; j >= 0 && src[i] < src[j]; j -= h){   //错错错 ‘j>=0’写出 ‘j>0’
                    temp = src[i];
                    src[i] = src[j];
                    src[j] = temp;
                }

                /*j = i - h;
                while (j > 0 && src[j] > src[j+h]){
                    //循环
                    int temp = src[j+h];
                    src[j+h] = src[j];
                    src[j] = temp;
                    j -= h;
                }*/
            }
        }
        print("希尔", src);
    }

    //自顶向下
    public static void mergeSort1(int [] src, int lo, int hi){
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort1(src, lo, mid);
        mergeSort1(src, mid + 1, hi);
        merge(src, lo, mid, hi);
    }
//自底向上
    public static void mergeSort2(int [] src){
        int length = src.length;
        int end;
        for (int size = 1; size < length; size+=size){
            for (int start = 0; start < length-size; start += size+size){//错错错
                end = Math.min(start+size+size-1, length-1);           //对于数组长度不满足2的x次幂的数组，mid可能会大于end
                merge(src, start, start+size-1, end);
            }
        }

    }

    public static void merge(int [] src, int lo, int mid, int hi){
        int length = src.length;
        int [] aux = new int[length];   //这个辅助数组应该定义到外面？
        for (int i = lo; i <= hi; i++){         //也不对 for (int i = 0; i < src.length; i++)
            aux[i] = src[i];
        }
        int start = lo, middle = mid + 1;             //错错错 没有写，自己直接使用参数！
        for (int i = lo; i <= hi; i++){       //一样 "="怎么判断
         if (start > mid) src[i] = aux[middle++];
         else if (middle > hi) src[i] = aux[start++];
         else if (aux[start] > aux[middle]) src[i] = aux[middle++];
         else src[i] = aux[start++];
        }
    }

    public static void quickSort(int[] a, int start, int end){
        if(start >= end) return;
        int i = partition(a, start, end);
        quickSort(a, start, i-1);
        quickSort(a, i+1, end);

    }
    private static int partition(int[] a, int start, int end){
        int v = a[start];
        int i = start, j = end + 1;          //为啥 j = end + 1;
        int temp;
        while (true) {
            while (a[++i] < v) if (i == end) break;  //错错错  (i > end)  i++
            while (a[--j] > v) ;
            if (i >= j) break;
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
//            a[i] = a[i] + a[j];
//            a[j] = a[i] - a[j];
//            a[i] = a[i] - a[j];
        }
        a[start] = a[j];
        a[j] = v;
//        a[j] = v + a[j];
//        v = a[j] - v;
//        a[i] = a[j] - v;
        return j;
    }

    public static void sink(int[] src, int target, int length){
        int k, temp;
        while (2 * target + 1 <= length){
            k = 2 * target + 1;
            if(k < length && src[k] < src[k+1]) k++;
            if (src[target] > src[k]) break;
            temp = src[target];
            src[target] = src[k];
            src[k] = temp;
            target = k;
        }
    }
//堆排序
    public static void tree(int[] src){
        int length = src.length ;
        int temp;
        for (int k = length / 2 - 1; k >= 0; k--)
            sink(src, k, length-1);
        for (int i = length - 1; i >= 0; i--){
            temp = src[0];
            src[0] = src[i];
            src[i] = temp;
            sink(src, 0, i - 1);
        }
    }


    public static void main(String[] args) {
        int [] src = new int []{3, 12, 5, 1, 9, 6, 16, 10, 8, 15, 0};
        print("原始",src);
        System.out.println();
        //select(src);
        //insert1(src);
        //insert2(src);
//        bubble(src);
        //shell(src);
        //mergeSort1(src, 0, src.length-1);
        //mergeSort2(src);
        //print("归并",src);
        //quickSort(src, 0, src.length - 1);
        //print("快速",src);
        //tree(src);
        //print("堆",src);
        //System.out.println(2&1); //000110 & 000001 = 0   或者 000111 & 000001 = 1 就是这个意思；  num 跟 num-1 二进制中每一位都不相同
    }

}
