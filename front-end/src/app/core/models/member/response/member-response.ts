export class MemberResponse {
    id!: number;
    username!: string;
    email!: string;
    firstName!: string;
    lastName!: string;
    gender!: 'MALE' | 'FEMALE' | 'OTHER';
    birthDate!: Date;
    country!: string;
    profileImageUrl!: string;
    biography!: string;
    website!: string;
    links!: string;
    socialMediaLinks!: string;
    createdDate!: Date;
    updatedDate!: Date;
}
