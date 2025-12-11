'use client';

import type { Book } from '@/types/Book';
import { saveToRecent } from '@/services/localStorage';
import styles from '@/styles/BookCard.module.css';

interface BookCardProps {
  book: Book;
}

export default function BookCard({ book }: BookCardProps) {
  const handleClick = () => {
    saveToRecent(book);
    window.open(book.infoLink, '_blank');
  };

  return (
    <div className={styles.card} onClick={handleClick}>
      <div className={styles.imageContainer}>
        {book.thumbnail ? (
          <img
            src={book.thumbnail}
            alt={book.title}
            className={styles.thumbnail}
          />
        ) : (
          <div className={styles.noImage}>Sin imagen</div>
        )}
      </div>
      
      <h3 className={styles.title}>{book.title}</h3>
      
      <p className={styles.authors}>
        {book.authors.join(', ')}
      </p>
      
      {book.publishedDate && (
        <p className={styles.date}>{book.publishedDate}</p>
      )}
    </div>
  );
}