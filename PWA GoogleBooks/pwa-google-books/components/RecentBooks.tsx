'use client';

import { useEffect, useState } from 'react';
import BookCard from './BookCard';
import { getRecentBooks } from '@/services/localStorage';
import type { Book } from '@/types/Book';
import styles from '@/styles/RecentBooks.module.css';

export default function RecentBooks() {
  const [recentBooks, setRecentBooks] = useState<Book[]>([]);

  useEffect(() => {
    const books = getRecentBooks();
    setRecentBooks(books);
  }, []);

  if (recentBooks.length === 0) {
    return (
      <div className={styles.recentSection}>
        <h2 className={styles.title}>Libros Recientes</h2>
        <p className={styles.noRecent}>
          Aún no has consultado ningún libro
        </p>
      </div>
    );
  }

  return (
    <div className={styles.recentSection}>
      <h2 className={styles.title}>
        Últimos Libros Consultados ({recentBooks.length})
      </h2>
      <div className={styles.recentGrid}>
        {recentBooks.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>
    </div>
  );
}
