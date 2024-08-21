import { PostLikeResponse } from "./post-like-response";
import { PostMediaResponse } from "./post-media-response";

export class PostResponse {
    id!: number;
    userId!: number;
    content!: string;
    createdDate!: Date;
    updatedDate!: Date;
    media!: PostMediaResponse[];
    likes!: PostLikeResponse[];
    commentCount!: number;
    username!: string;
    profilePicture!: string;
}
