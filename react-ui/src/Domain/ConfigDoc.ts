import Namespace from "./Namespace";
import Property from "./Property";
import Version from './Version';

// tslint:disable-next-line:interface-name
export interface ConfigDoc {
    readonly moduleName: string;
    readonly moduleVersion: string;
    readonly generatorVersion: Version;
    readonly namespaces: ReadonlyArray<Namespace>;
    readonly properties: ReadonlyArray<Property>;
}
