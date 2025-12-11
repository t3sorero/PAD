export interface Book {
  id: string;
  title: string;
  authors: string[];
  thumbnail?: string;
  description?: string;
  publishedDate?: string;
  infoLink: string;
}

export interface GoogleBooksVolumeInfo {
  title: string;
  authors?: string[];
  description?: string;
  publishedDate?: string;
  imageLinks?: {
    thumbnail?: string;
    smallThumbnail?: string;
  };
  infoLink: string;
}

export interface GoogleBooksItem {
  id: string;
  volumeInfo: GoogleBooksVolumeInfo;
}

export interface GoogleBooksResponse {
  items?: GoogleBooksItem[];
  totalItems: number;
}