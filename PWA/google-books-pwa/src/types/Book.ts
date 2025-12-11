export interface Book {
  id: string;
  title: string;
  authors: string[];
  infoLink: string;
  thumbnail?: string;
  description?: string;
  publishedDate?: string;
}

export interface GoogleBooksResponse {
  items?: Array<{
    id: string;
    volumeInfo: {
      title: string;
      authors?: string[];
      infoLink: string;
      imageLinks?: {
        thumbnail: string;
      };
      description?: string;
      publishedDate?: string;
    };
  }>;
}