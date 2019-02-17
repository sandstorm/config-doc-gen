import Namespace from "./Namespace";
import Property from "./Property";
import Version from './Version';

// tslint:disable-next-line:interface-name
export interface ConfigDoc {
    readonly moduleName: string;
    readonly version: Version;
    readonly namespaces: ReadonlyArray<Namespace>;
    readonly properties: ReadonlyArray<Property>;
}
