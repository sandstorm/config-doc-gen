import Namespace from "./Namespace";
import Property from "./Property";

// tslint:disable-next-line:interface-name
export interface ConfigDoc {
    readonly moduleName: string;
    readonly processorVersion: string;
    readonly namespaces: ReadonlyArray<Namespace>;
    readonly properties: ReadonlyArray<Property>;
}
