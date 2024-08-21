import { PostMediaCreateRequest } from "./post-media-create-request";

export class PostCreateRequest {
    userId!: number;
    content!: string;
    media!: PostMediaCreateRequest[];
}
