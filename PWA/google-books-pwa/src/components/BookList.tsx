import { BookCard } from './BookCard';
import type { Book } from '../types/Book';
import styles from '../styles/BookList.module.css';

interface BookListProps {
  books: Book[];
}

export const BookList = ({ books }: BookListProps) => {
  return (
    <div className={styles.bookList}>
      {books.map(book => (
        <BookCard key={book.id} book={book} />
      ))}
    </div>
  );
};