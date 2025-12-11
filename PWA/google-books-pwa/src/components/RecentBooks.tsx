import { useState, useEffect } from 'react';
import { getRecentBooks } from '../services/localStorage';
import { BookCard } from './BookCard';
import styles from '../styles/RecentBooks.module.css';
import { Book } from '@/types/Book';

export const RecentBooks = () => {
  const [recentBooks, setRecentBooks] = useState<Book[]>([]);

  useEffect(() => {
    const books = getRecentBooks();
    setRecentBooks(books);
  }, []);

  if (recentBooks.length === 0) {
    return null;
  }

  return (
    <div className={styles.recentBooks}>
      <h2 className={styles.title}>📚 Libros consultados recientemente</h2>
      <div className={styles.bookList}>
        {recentBooks.map(book => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>
    </div>
  );
};