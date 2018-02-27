package cn.codingblock.ipc.aidl;

import cn.codingblock.ipc.aidl.Book;

interface IBookManager{
    List<Book> getBookList();
    void addBook(in Book book);
}