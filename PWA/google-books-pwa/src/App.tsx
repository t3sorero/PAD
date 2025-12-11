import { useState } from 'react';
import { SearchBar } from './components/SearchBar';
import { BookList } from './components/BookList';
import { RecentBooks } from './components/RecentBooks';
import { useBookSearch } from './hooks/useBookSearch';
import './styles/global.css';

function App() {
  const { books, isLoading, error, performSearch } = useBookSearch();
  const [searchPerformed, setSearchPerformed] = useState(false);

  const handleSearch = (title: string) => {
    setSearchPerformed(true);
    performSearch(title);
  };

  return (
    <div className="container">
      <header style={{ textAlign: 'center', marginBottom: '40px' }}>
        <h1 style={{ fontSize: '36px', color: '#1976d2', marginBottom: '10px' }}>
          Buscador de Libros
        </h1>
        <p style={{ color: '#666' }}>
          Busca libros en Google Books
        </p>
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
        <>
          <h2 style={{ textAlign: 'center', marginBottom: '20px' }}>
            Resultados ({books.length})
          </h2>
          <BookList books={books} />
        </>
      )}

      {!searchPerformed && !isLoading && (
        <RecentBooks />
      )}
    </div>
  );
}

export default App;