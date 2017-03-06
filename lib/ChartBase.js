import {PropTypes} from 'react';
import {
  View
} from 'react-native';

import {xAxisIface} from './AxisIface'

const descriptionIface = {
  text: PropTypes.string,
  textColor: PropTypes.string,
  textSize: PropTypes.number,

  positionX: PropTypes.number,
  positionY: PropTypes.number,

  fontFamily: PropTypes.string,
  fontStyle: PropTypes.number
};

const legendIface = {
  enabled: PropTypes.bool,

  textColor: PropTypes.string,
  textSize: PropTypes.number,
  fontFamily: PropTypes.string,
  fontStyle: PropTypes.number,

  wordWrapEnabled: PropTypes.bool,
  maxSizePercent: PropTypes.number,

  position: PropTypes.string,
  form: PropTypes.string,
  formSize: PropTypes.number,
  xEntrySpace: PropTypes.number,
  yEntrySpace: PropTypes.number,
  formToTextSpace: PropTypes.number,

  custom: PropTypes.shape({
    colors: PropTypes.arrayOf(PropTypes.string),
    labels: PropTypes.arrayOf(PropTypes.string)
  })
};

const chartIface = {
  propTypes: {
    ...View.propTypes,

    animation: PropTypes.shape({
      durationX: PropTypes.number,
      durationY: PropTypes.number,

      easingX: PropTypes.string,
      easingY: PropTypes.string
    }),

    backgroundColor: PropTypes.string,
    logEnabled: PropTypes.bool,
    noDataText: PropTypes.string,

    touchEnabled: PropTypes.bool,
    dragDecelerationEnabled: PropTypes.bool,
    dragDecelerationFrictionCoef: (props, propName, componentName) => {
      let coef = props[propName];
      if (coef && (typeof coef !== 'number' || coef < 0 || coef > 1)) {
        return new Error(
          `Invalid prop ${propName} supplied to '${componentName}'. Value must be number and between 0 and 1.`
        );
      }
    },

    description: PropTypes.shape(descriptionIface),

    legend: PropTypes.shape(legendIface),

    xAxis: PropTypes.shape(xAxisIface),

    marker: PropTypes.shape({
      enabled: PropTypes.bool,
      type: PropTypes.oneOfType([
        PropTypes.oneOf(['rectangle', 'oval']),
        PropTypes.string
      ]),
      backgroundTint: PropTypes.string,
      textColor: PropTypes.string,
      textSize: PropTypes.number,
      fontFamily: PropTypes.string,
      fontStyle: PropTypes.number
    }),
  }
};

export default chartIface;
