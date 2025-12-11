'use client';

import BookCard from './BookCard';
import type { Book } from '@/types/Book';
import styles from '@/styles/BookList.module.css';

interface BookListProps {
  books: Book[];
}

export default function BookList({ books }: BookListProps) {
  if (books.length === 0) {
    return (
      <p className={styles.noResults}>
        No se encontraron resultados
      </p>
    );
  }

  return (
    <>
      <h2 className={styles.resultsTitle}>
        Resultados ({books.length})
      </h2>
      <div className={styles.grid}>
        {books.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>
    </>
  );
}