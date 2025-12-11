import type { Book } from '../types/Book';

const STORAGE_KEY = 'recentBooks';
const MAX_RECENT_BOOKS = 5;

export const saveBookToHistory = (book: Book): void => {
  try {
    let history = getRecentBooks();
    
    // Evitar duplicados
    history = history.filter(b => b.id !== book.id);
    
    // Añadir al inicio
    history.unshift(book);
    
    // Limitar a 5
    if (history.length > MAX_RECENT_BOOKS) {
      history = history.slice(0, MAX_RECENT_BOOKS);
    }
    
    localStorage.setItem(STORAGE_KEY, JSON.stringify(history));
  } catch (error) {
    console.error('Error saving to localStorage:', error);
  }
};

export const getRecentBooks = (): Book[] => {
  try {
    const stored = localStorage.getItem(STORAGE_KEY);
    return stored ? JSON.parse(stored) : [];
  } catch (error) {
    console.error('Error reading from localStorage:', error);
    return [];
  }
};

export const clearHistory = (): void => {
  try {
    localStorage.removeItem(STORAGE_KEY);
  } catch (error) {
    console.error('Error clearing localStorage:', error);
  }
};