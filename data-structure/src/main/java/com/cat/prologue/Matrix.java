package com.cat.prologue;

import java.lang.annotation.ElementType;

/**
 * <p>Title: Matrix</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/11/2 21:36
 **/
public class Matrix {


    //返回一个M*N的空矩阵
    Matrix create() {
        return null;
    }

    //返回矩阵A的总行数
    int getmaxrow(Matrix A) {
        return -1;
    }

    //返回矩阵A的总列数
    int getmaxcol(Matrix A) {
        return -1;
    }

    //返回矩阵A的第i行、第j列的元素
    ElementType getEntry(Matrix A, Matrix B) {
        return null;
    }

    //如果A和B的行、列数一致，返回矩阵C=A+B,否则返回错误标志
    Matrix add(Matrix A, Matrix B) {
        return null;
    }

    //如果A的列数等于B的行数，则返回矩阵C=AB,否则返回错误标志
    Matrix multiply(Matrix A, Matrix B) {
        return null;
    }
}
