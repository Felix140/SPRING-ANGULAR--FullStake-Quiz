import { Topic } from "./topic";

export interface Question {
    id: number;
    domanda: string;
    topicEntity: Topic;
    risposte: any;
}