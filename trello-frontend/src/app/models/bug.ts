import { Developer } from './developer';

export interface Bug{
    id?: number;
    name: string;
    description: string;
    priority: string;
    progress: string;
    creation_date: Date;
    developers: Developer
}