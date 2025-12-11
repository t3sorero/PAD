import { saveBookToHistory } from '../services/localStorage';
import type { Book } from '../types/Book';
import styles from '../styles/BookCard.module.css';

interface BookCardProps {
  book: Book;
}

export const BookCard = ({ book }: BookCardProps) => {
  const handleClick = () => {
    saveBookToHistory(book);
    window.open(book.infoLink, '_blank');
  };

  return (
    <div className={styles.card} onClick={handleClick}>
      {book.thumbnail && (
        <img 
          src={book.thumbnail} 
          alt={book.title}
          className={styles.thumbnail}
        />
      )}
      <div className={styles.content}>
        <h3 className={styles.title}>{book.title}</h3>
        <p className={styles.authors}>
          {book.authors.join(', ')}
        </p>
        {book.publishedDate && (
          <p className={styles.date}>{book.publishedDate}</p>
        )}
      </div>
    </div>
  );
};