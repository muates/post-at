import { PostCommentLikeResponse } from "./post-comment-like-response";

export class PostCommentResponse {
    id!: number;
    userId!: number;
    content!: string;
    createdDate!: Date;
    updatedDate!: Date;
    likes!: PostCommentLikeResponse[];
    username!: string;
    profilePicture!: string;
}
