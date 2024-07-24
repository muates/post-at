import { PostCommentResponse } from "./post-comment-response";
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
    comments!: PostCommentResponse[];
    username!: string;
    profilePicture!: string;
}
