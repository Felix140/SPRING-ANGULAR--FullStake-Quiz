import { Topic } from "./topic";

export interface Question {
    id: number;
    domanda: string;
    topic: Topic;
}