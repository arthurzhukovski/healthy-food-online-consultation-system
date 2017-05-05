export class Message{
    id: number;
    text: string;
    createdAt: Date;

    receiver: any = {};
    sender: any = {};
}