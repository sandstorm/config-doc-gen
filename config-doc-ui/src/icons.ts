import {fasIcon} from "./font-awesome";
import {IconDefinition} from '@fortawesome/fontawesome-svg-core'
import {Accessibility} from "./Domain/Accessibility";

export const apiPropertyIcon: IconDefinition = fasIcon('clipboard-check');
export const implementationPropertyIcon: IconDefinition = fasIcon('exclamation-circle');
export const unknownPropertyIcon: IconDefinition = fasIcon('question');
export const noPropertiesIcon: IconDefinition = fasIcon('radiation');

export function iconForAccessibility(accessibility: Accessibility): IconDefinition {
    switch (accessibility) {
        case Accessibility.API:
            return apiPropertyIcon;
        case Accessibility.IMPLEMENTATION:
            return implementationPropertyIcon;
        default:
            return unknownPropertyIcon;
    }
}
