import {fasIcon} from "./font-awesome";
import {IconDefinition} from '@fortawesome/fontawesome-svg-core'
import {UiItemType} from "./Domain/Ui/UiItem";

export const namespaceIcon: IconDefinition = fasIcon('cube');
export const apiPropertyIcon: IconDefinition = fasIcon('clipboard-check');
export const implementationPropertyIcon: IconDefinition = fasIcon('exclamation-circle');
export const unknownIcon: IconDefinition = fasIcon('question');
export const noPropertiesIcon: IconDefinition = fasIcon('radiation');

export function iconForItem(type: UiItemType) {
    switch (type) {
        case UiItemType.NAMESPACE:
            return namespaceIcon;
        case UiItemType.PROPERTY_API:
            return apiPropertyIcon;
        case UiItemType.PROPERTY_IMPLEMENTATION:
            return implementationPropertyIcon;
        default:
            return unknownIcon;
    }
}
