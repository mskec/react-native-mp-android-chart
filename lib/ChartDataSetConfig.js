import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';


const chartDataSetConfig = {
  common: {
    colors: PropTypes.arrayOf(PropTypes.string),
    highlightEnabled:PropTypes.bool,
    drawValues: PropTypes.bool,
    valueTextSize:PropTypes.number,
    visible:PropTypes.bool,
  },

  barLineScatterCandleBubble: {
    highlightColor: PropTypes.string
  },

  lineScatterCandleRadar: {
    drawVerticalHighlightIndicator: PropTypes.bool,
    drawHorizontalHighlightIndicator: PropTypes.bool,
    highlightLineWidth: PropTypes.number
  },

  lineRadar: {
    fillColor: PropTypes.string,
    fillAlpha: PropTypes.number,
    drawFilled: PropTypes.bool,
    lineWidth: (props, propName, componentName) => {
      let lineWidth = props[propName];
      if (lineWidth && (typeof lineWidth !== 'number' || lineWidth < 0.2 || lineWidth > 10)) {
        return new Error(
          `Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0.2f and 10f`
        );
      }
    }
  }
};

export default chartDataSetConfig;
