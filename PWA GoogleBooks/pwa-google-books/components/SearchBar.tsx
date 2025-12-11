'use client';

import { useState, FormEvent } from 'react';
import styles from '@/styles/SearchBar.module.css';

interface SearchBarProps {
  onSearch: (title: string) => void;
  isLoading: boolean;
}

export default function SearchBar({ onSearch, isLoading }: SearchBarProps) {
  const [searchTerm, setSearchTerm] = useState('');

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault();
    if (searchTerm.trim()) {
      onSearch(searchTerm.trim());
    }
  };

  return (
    <div className={styles.searchContainer}>
      <form onSubmit={handleSubmit} className={styles.searchForm}>
        <input
          type="text"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
          placeholder="Buscar libros por título..."
          className={styles.searchInput}
          disabled={isLoading}
        />
        <button
          type="submit"
          className={styles.searchButton}
          disabled={isLoading || !searchTerm.trim()}
        >
          {isLoading ? 'Buscando...' : 'Buscar'}
        </button>
      </form>
    </div>
  );
}