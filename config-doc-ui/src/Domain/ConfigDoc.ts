import {Property} from "./Property";
import {Namespace} from "./Namespace";

export interface ConfigDoc {
    readonly namespaces: ReadonlyArray<Namespace>;
    readonly properties: ReadonlyArray<Property>;
}
