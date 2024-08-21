export class Page<T> {
    content: T[] = [];
    size: number = 0;
    totalElements: number = 0;
    totalPages: number = 0;
    number: number = 0;
  
    constructor(init?: Partial<Page<T>>) {
      Object.assign(this, init);
    }
}
