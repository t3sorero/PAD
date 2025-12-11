import { useState } from 'react';
import { searchBooks } from '@/services/googleBooksApi';
import { saveToRecent } from '@/services/localStorage';
import type { Book } from '@/types/Book';

export const useBookSearch = () => {
  const [books, setBooks] = useState<Book[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const performSearch = async (title: string) => {
    if (!title.trim()) {
      setError('Por favor, introduce un título');
      return;
    }

    setIsLoading(true);
    setError(null);

    try {
      const results = await searchBooks(title);
      setBooks(results);
      
      if (results.length === 0) {
        setError('No se encontraron resultados');
      }
    } catch (err) {
      setError('Error al buscar libros. Intenta de nuevo.');
      setBooks([]);
      console.error('Error en búsqueda:', err);
    } finally {
      setIsLoading(false);
    }
  };

  return { books, isLoading, error, performSearch };
};