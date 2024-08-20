export class PostMediaCreateRequest {
    mediaUrl!: string;
    mediaType!: MediaType;
}

export enum MediaType {
    IMAGE = 'IMAGE',
    VIDEO = 'VIDEO'
}