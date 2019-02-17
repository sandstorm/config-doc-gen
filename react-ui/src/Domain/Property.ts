import {Accessibility} from "./Accessibility";
import PropertyType from './PropertyType';

// tslint:disable-next-line:interface-name
export default interface Property {
    readonly name: string;
    readonly qualifiedName: string;
    readonly namespace: string;
    readonly accessibility: Accessibility;
    readonly documentationContent: string;
    readonly type: PropertyType;
}