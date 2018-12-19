import {Accessibility} from "./Accessibility";

// tslint:disable-next-line:interface-name
export default interface Property {
    readonly name: string;
    readonly qualifiedName: string;
    readonly namespace: string;
    readonly accessibility: Accessibility;
    readonly documentation: string;
}
