import type { Book } from '../types/Book';

const RECENT_BOOKS_KEY = 'recentBooks';
const MAX_RECENT_BOOKS = 5;

export const saveToRecent = (book: Book): void => {
  if (typeof window === 'undefined') return;

  try {
    const recent = getRecentBooks();
    
    // Eliminar el libro si ya existe para evitar duplicados
    const filtered = recent.filter(b => b.id !== book.id);
    
    // Añadir al principio
    const updated = [book, ...filtered].slice(0, MAX_RECENT_BOOKS);
    
    localStorage.setItem(RECENT_BOOKS_KEY, JSON.stringify(updated));
  } catch (error) {
    console.error('Error saving to localStorage:', error);
  }
};

export const getRecentBooks = (): Book[] => {
  if (typeof window === 'undefined') return [];

  try {
    const stored = localStorage.getItem(RECENT_BOOKS_KEY);
    return stored ? JSON.parse(stored) : [];
  } catch (error) {
    console.error('Error reading from localStorage:', error);
    return [];
  }
};

export const clearRecentBooks = (): void => {
  if (typeof window === 'undefined') return;

  try {
    localStorage.removeItem(RECENT_BOOKS_KEY);
  } catch (error) {
    console.error('Error clearing localStorage:', error);
  }
};