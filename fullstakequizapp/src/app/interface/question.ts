
export interface Question {
    id: number;
    domanda: string;
    topicEntity: {
        id: number | null
    },
    risposte: any;
}