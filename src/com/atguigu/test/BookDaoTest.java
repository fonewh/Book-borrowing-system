package com.atguigu.test;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"剑来","烽火戏诸侯",new BigDecimal(1111111),99999,0,null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(20);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(20,"剑来","烽火戏诸侯",new BigDecimal(9999),1,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(20));
    }

    @Test
    public void queryBook() {
        for (Book queryBook:bookDao.queryBook()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());

    }

    @Test
    public void queryForPageItems() {
        System.out.println(bookDao.queryForPageItems(4, Page.PAGE_SIZE));
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,50));

    }

    @Test
    public void queryForPageItemsByPrice() {
        System.out.println(bookDao.queryForPageItemsByPrice(4,Page.PAGE_SIZE,10,50));
    }
}