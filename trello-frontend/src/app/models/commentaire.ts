import { Bug } from './bug';
import { Developer } from './developer';

export interface Commentaire {
    id?: number;
    texte: number;
    bugs: Bug;
    developers: Developer
}