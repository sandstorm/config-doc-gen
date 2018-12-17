import {Accessibility} from "./Accessibility";

export interface Property {
    readonly name: string;
    readonly qualifiedName: string;
    readonly namespace: string;
    readonly accessibility: Accessibility;
    readonly documentation: string;
}
