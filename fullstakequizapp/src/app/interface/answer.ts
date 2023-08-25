import { Question } from "./question";

export interface Answer {
    id: number;
    esito: boolean;
    risposta: string;
    quiz: Question;
}