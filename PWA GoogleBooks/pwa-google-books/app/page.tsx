'use client';

import { useState } from 'react';
import SearchBar from '@/components/SearchBar';
import BookList from '@/components/BookList';
import RecentBooks from '@/components/RecentBooks';
import { useBookSearch } from '@/hooks/useBookSearch';

export default function Home() {
  const { books, isLoading, error, performSearch } = useBookSearch();
  const [searchPerformed, setSearchPerformed] = useState(false);

  const handleSearch = (title: string) => {
    setSearchPerformed(true);
    performSearch(title);
  };

  return (
    <div className="container">
      <header>
        <h1>Buscador de Libros</h1>
        <p className="subtitle">Busca libros en Google Books</p>
      </header>

      <SearchBar onSearch={handleSearch} isLoading={isLoading} />

      {isLoading && (
        <p style={{ textAlign: 'center', fontSize: '18px', color: '#666' }}>
          Cargando...
        </p>
      )}

      {error && !isLoading && (
        <p style={{ textAlign: 'center', fontSize: '18px', color: '#d32f2f' }}>
          {error}
        </p>
      )}

      {!isLoading && !error && books.length > 0 && (
        <BookList books={books} />
      )}

      {!searchPerformed && !isLoading && <RecentBooks />}
    </div>
  );
}