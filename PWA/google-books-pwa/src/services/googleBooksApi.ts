import axios from 'axios';
import type { Book, GoogleBooksResponse } from '../types/Book';

const API_BASE_URL = 'https://www.googleapis.com/books/v1/volumes';
const API_KEY = import.meta.env.VITE_GOOGLE_BOOKS_API_KEY;

export const searchBooks = async (title: string): Promise<Book[]> => {
  try {
    const response = await axios.get<GoogleBooksResponse>(API_BASE_URL, {
      params: {
        q: `intitle:${title}`,
        printType: 'books',      
        key: API_KEY,
        maxResults: 20,
      },
    });

    return parseGoogleBooksResponse(response.data);
  } catch (error) {
    console.error('Error fetching books:', error);
    throw new Error('Error al buscar libros');
  }
};

const parseGoogleBooksResponse = (data: GoogleBooksResponse): Book[] => {
  if (!data.items) {
    return [];
  }

  return data.items.map((item) => ({
    id: item.id,
    title: item.volumeInfo.title,
    authors: item.volumeInfo.authors || ['Autor desconocido'],
    infoLink: item.volumeInfo.infoLink,
    thumbnail: item.volumeInfo.imageLinks?.thumbnail,
    description: item.volumeInfo.description,
    publishedDate: item.volumeInfo.publishedDate,
  }));
};
