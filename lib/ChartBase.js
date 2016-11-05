import {PropTypes} from 'react';
import {
  View
} from 'react-native';


export const axisIface = {
  // what is drawn
  enabled: PropTypes.bool,
  drawLabels: PropTypes.bool,
  drawAxisLine: PropTypes.bool,
  drawGridLines: PropTypes.bool,

  // style
  textColor: PropTypes.string,
  textSize: PropTypes.number,
  fontFamily: PropTypes.string,
  fontStyle: PropTypes.number,
  gridColor: PropTypes.string,
  gridLineWidth: PropTypes.number,
  axisLineColor: PropTypes.string,
  axisLineWidth: PropTypes.number,
  gridDashedLine: PropTypes.shape({
    lineLength: PropTypes.number,
    spaceLength: PropTypes.number,
    phase: PropTypes.number
  }),

  // limit lines
  limitLines: PropTypes.arrayOf(
    PropTypes.shape({
      limit: PropTypes.number.isRequired,
      label: PropTypes.string,
      lineColor: PropTypes.string,
      lineWidth: PropTypes.number,
    })
  ),
  drawLimitLinesBehindData: PropTypes.bool
};

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
    noDataTextDescription: PropTypes.string,

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

    xAxis: PropTypes.shape({
      ...axisIface,

      labelsToSkip: PropTypes.number,
      avoidFirstLastClipping: PropTypes.bool,
      spaceBetweenLabels: PropTypes.number,
      position: PropTypes.string
    }),
    marker: PropTypes.shape({
      enabled: PropTypes.bool,
      markerColor: PropTypes.string,
      markerTextColor: PropTypes.string,
      markerFontName: PropTypes.string,
      markerFontSize: PropTypes.number,
    }),
  }
};

export default chartIface;
