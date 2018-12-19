import Namespace from "./Namespace";
import Property from "./Property";

// tslint:disable-next-line:interface-name
export interface ConfigDoc {
    readonly namespaces: ReadonlyArray<Namespace>;
    readonly properties: ReadonlyArray<Property>;
}
